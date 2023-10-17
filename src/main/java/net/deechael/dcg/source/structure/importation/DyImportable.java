package net.deechael.dcg.source.structure.importation;

public interface DyImportable {

    void imports(DyExportable... types);

    void imports(Iterable<DyExportable> types);

}
