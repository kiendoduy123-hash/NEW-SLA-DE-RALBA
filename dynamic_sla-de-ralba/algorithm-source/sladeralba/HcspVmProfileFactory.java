package sladeralba;

import java.util.List;

/** Selects the paper-project VM profile corresponding to an HCSP matrix label. */
public final class HcspVmProfileFactory {
    private HcspVmProfileFactory() {
    }

    public static List<VMStatusTable> create(String scenario, int vmPes) {
        switch (scenario.toLowerCase()) {
            case "u_c_hihi": return VmCreatorForU_C.createVM_u_c_hihi(vmPes);
            case "u_c_hilo": return VmCreatorForU_C.createVM_u_c_hilo(vmPes);
            case "u_c_lohi": return VmCreatorForU_C.createVM_u_c_lohi(vmPes);
            case "u_c_lolo": return VmCreatorForU_C.createVM_u_c_lolo(vmPes);
            case "u_i_hihi": return VmCreatorForU_I.createVM_u_i_hihi(vmPes);
            case "u_i_hilo": return VmCreatorForU_I.createVM_u_i_hilo(vmPes);
            case "u_i_lohi": return VmCreatorForU_I.createVM_u_i_lohi(vmPes);
            case "u_i_lolo": return VmCreatorForU_I.createVM_u_i_lolo(vmPes);
            case "u_s_hihi": return VmCreatorForU_S.createVM_u_s_hihi(vmPes);
            case "u_s_hilo": return VmCreatorForU_S.createVM_u_s_hilo(vmPes);
            case "u_s_lohi": return VmCreatorForU_S.createVM_u_s_lohi(vmPes);
            case "u_s_lolo": return VmCreatorForU_S.createVmsforlolo(vmPes);
            default: throw new IllegalArgumentException("Unsupported HCSP scenario: " + scenario);
        }
    }
}
