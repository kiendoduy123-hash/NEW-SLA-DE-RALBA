# Source provenance

The SLA-DE-RALBA source was obtained from the authors' public Bitbucket repository:
<https://bitbucket.org/sladeralba_26/dynamic-sla-de-ralba/src/main/>.

The unmodified archive is preserved at `dynamic_sla-de-ralba/upstream/sladeralba-bitbucket-main.zip`.

## Files matching the upstream archive byte-for-byte

- `CloudletStatusTable.java`
- `VMCreator.java`
- `VmCreatorForU_C.java`
- `VmCreatorForU_I.java`
- `VmCreatorForU_S.java`
- `VMStatusTable.java`

## Local changes and additions

- `SLADERALBA.java` differs from the upstream archive. The project copy adds a configurable HCSP entry path, explicit HCSP mode and runtime handling, and standardized metric/result logging used by the reproduction runner. The GoCJ scheduler remains based on the author's source; this file is not an untouched upstream copy.
- `HcspCsvAdapter.java` and `HcspVmProfileFactory.java` are project additions and are absent from the upstream archive. They support an HCSP adaptation; no adapted HCSP measurements are presented as comparable results in this repository.
- The Maven configuration and GoCJ runner are project reproduction infrastructure.

The GoCJ result comparison is descriptive. See the later GoCJ report and source metadata before interpreting differences as algorithm improvements.
