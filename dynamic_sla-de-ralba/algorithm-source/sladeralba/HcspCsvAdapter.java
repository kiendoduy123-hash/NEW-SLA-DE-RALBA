package sladeralba;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Makes the undocumented HCSP-to-MI/SLA/arrival adaptation explicit and repeatable.
 * ETC is converted to one scalar MI estimate by averaging ETC(task,vm)*MIPS(vm).
 * SLA and arrival columns are borrowed by row order from the author-provided
 * Enhanced GoCJ 1000-job file; these two fields are an assumption, not HCSP data.
 */
public final class HcspCsvAdapter {
    private static final int EXPECTED_TASKS = 512;
    private static final int EXPECTED_MACHINES = 16;
    private static final int MAX_ARRIVAL = 5000;

    private HcspCsvAdapter() {
    }

    public static Preparation prepare(Path etcPath, Path goCjPath, Path csvPath,
            String scenario, List<VMStatusTable> vms) throws IOException {
        double[][] etc = readEtcMatrix(etcPath);
        List<int[]> metadata = readSlaArrival(goCjPath, etc.length);
        if (vms.size() != EXPECTED_MACHINES) {
            throw new IOException("HCSP profile must contain 16 VMs; found " + vms.size());
        }

        List<String> rows = new ArrayList<>();
        rows.add("MI,SLA,arrival");
        double totalMi = 0;
        int maxArrival = 0;
        for (int task = 0; task < etc.length; task++) {
            double equivalentMi = 0;
            for (int machine = 0; machine < vms.size(); machine++) {
                equivalentMi += etc[task][machine] * vms.get(machine).getMips();
            }
            equivalentMi /= vms.size();
            long mi = Math.max(1L, Math.round(equivalentMi));
            int sla = metadata.get(task)[0];
            int arrival = metadata.get(task)[1];
            rows.add(String.format(Locale.ROOT, "%d,%d,%d", mi, sla, arrival));
            totalMi += mi;
            maxArrival = Math.max(maxArrival, arrival);
        }

        Path parent = csvPath.toAbsolutePath().getParent();
        if (parent != null) Files.createDirectories(parent);
        Files.write(csvPath, rows, StandardCharsets.UTF_8);
        writeManifest(csvPath, etcPath, goCjPath, scenario, vms, etc.length);

        double totalMips = vms.stream().mapToDouble(VMStatusTable::getMips).sum();
        // Use a conservative multiple of the aggregate-throughput makespan estimate.
        // A scheduling tick is then chosen to keep the event count bounded; for long
        // HCSP cases, this introduces at most one tick of arrival-time quantization.
        double aggregateMakespan = totalMi / (totalMips * 0.70);
        double termination = maxArrival + aggregateMakespan * 5 + 3_000;
        double postArrivalInterval = Math.max(10, Math.ceil((termination - maxArrival) / 2_500));
        return new Preparation(totalMi, maxArrival, termination, 10, postArrivalInterval, etc.length);
    }

    private static double[][] readEtcMatrix(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        List<String> tokens = new ArrayList<>();
        for (String line : lines) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) tokens.add(tokenizer.nextToken());
        }
        if (tokens.size() < 3) throw new IOException("HCSP file is missing its 3-value header: " + path);
        int tasks;
        int machines;
        try {
            tasks = Integer.parseInt(tokens.get(0));
            machines = Integer.parseInt(tokens.get(1));
        } catch (NumberFormatException ex) {
            throw new IOException("Invalid HCSP dimensions in " + path, ex);
        }
        if (tasks != EXPECTED_TASKS || machines != EXPECTED_MACHINES) {
            throw new IOException("Expected HCSP 512x16, found " + tasks + "x" + machines);
        }
        if (tokens.size() != 3 + tasks * machines) {
            throw new IOException("Expected " + (tasks * machines) + " ETC cells, found " + (tokens.size() - 3));
        }
        double[][] matrix = new double[tasks][machines];
        for (int i = 0, k = 3; i < tasks; i++) {
            for (int j = 0; j < machines; j++, k++) {
                try {
                    matrix[i][j] = Double.parseDouble(tokens.get(k));
                } catch (NumberFormatException ex) {
                    throw new IOException("Invalid ETC value at task " + i + ", machine " + j, ex);
                }
                if (!Double.isFinite(matrix[i][j]) || matrix[i][j] <= 0) {
                    throw new IOException("ETC must be finite and positive at task " + i + ", machine " + j);
                }
            }
        }
        return matrix;
    }

    private static List<int[]> readSlaArrival(Path path, int count) throws IOException {
        List<int[]> values = new ArrayList<>();
        for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
            String[] fields = line.split(",");
            if (fields.length < 3) continue;
            try {
                int sla = Integer.parseInt(fields[1].trim());
                int arrival = Integer.parseInt(fields[2].trim());
                if (sla >= 1 && sla <= 3 && arrival >= 0 && arrival <= MAX_ARRIVAL) {
                    values.add(new int[] {sla, arrival});
                    if (values.size() == count) break;
                }
            } catch (NumberFormatException ignored) {
                // Ignore the optional header or malformed rows, matching paper preprocessing.
            }
        }
        if (values.size() < count) {
            throw new IOException("Need " + count + " valid SLA/arrival rows in " + path
                    + "; found " + values.size());
        }
        return values;
    }

    private static void writeManifest(Path csvPath, Path etcPath, Path goCjPath,
            String scenario, List<VMStatusTable> vms, int tasks) throws IOException {
        List<String> mips = new ArrayList<>();
        for (VMStatusTable vm : vms) mips.add(Integer.toString(vm.getMips()));
        String json = "{\n"
                + "  \"scenario\": \"" + scenario + "\",\n"
                + "  \"tasks\": " + tasks + ",\n"
                + "  \"machines\": 16,\n"
                + "  \"etc_source\": \"" + etcPath.toString().replace("\\", "\\\\") + "\",\n"
                + "  \"etc_sha256\": \"" + sha256(etcPath) + "\",\n"
                + "  \"sla_arrival_source\": \"" + goCjPath.toString().replace("\\", "\\\\") + "\",\n"
                + "  \"sla_arrival_rule\": \"first 512 valid rows in source order; SLA in 1..3 and arrival in 0..5000\",\n"
                + "  \"mi_rule\": \"round(mean over 16 machines of ETC(task,machine)*VM_MIPS(machine))\",\n"
                + "  \"vm_mips\": [" + String.join(", ", mips) + "],\n"
                + "  \"arrival_simulation_tick_seconds\": 10,\n"
                + "  \"post_last_arrival_tick_seconds\": \"max(10, ceil((termination_time-max_arrival)/2500))\",\n"
                + "  \"assumption\": \"HCSP source has no SLA/arrival; this adaptation is not the unpublished author HCSP CSV\"\n"
                + "}\n";
        Path manifest = csvPath.resolveSibling(csvPath.getFileName().toString().replaceFirst("\\.csv$", ".json"));
        Files.writeString(manifest, json, StandardCharsets.UTF_8);
    }

    private static String sha256(Path path) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(digest.digest(Files.readAllBytes(path)));
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 is unavailable", ex);
        }
    }

    public static final class Preparation {
        private final double totalMi;
        private final int maxArrival;
        private final double terminationTime;
        private final double schedulingInterval;
        private final double postArrivalInterval;
        private final int taskCount;

        private Preparation(double totalMi, int maxArrival, double terminationTime,
                double schedulingInterval, double postArrivalInterval, int taskCount) {
            this.totalMi = totalMi;
            this.maxArrival = maxArrival;
            this.terminationTime = terminationTime;
            this.schedulingInterval = schedulingInterval;
            this.postArrivalInterval = postArrivalInterval;
            this.taskCount = taskCount;
        }

        public double getTotalMi() { return totalMi; }
        public int getMaxArrival() { return maxArrival; }
        public double getTerminationTime() { return terminationTime; }
        public double getSchedulingInterval() { return schedulingInterval; }
        public double getPostArrivalInterval() { return postArrivalInterval; }
        public int getTaskCount() { return taskCount; }
    }
}
