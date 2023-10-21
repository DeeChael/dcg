package net.deechael.dcg.source.structure;

import net.deechael.dcg.source.type.DyType;

import java.util.List;

public interface DyParameterable {

    DyParameter addParameter(DyType type, String name);

    List<DyParameter> listParameters();

}
