package net.deechael.dcg.structure.importation;

public interface DyImportable {

    void imports(DyExportable... types);

    void imports(Iterable<DyExportable> types);

}
