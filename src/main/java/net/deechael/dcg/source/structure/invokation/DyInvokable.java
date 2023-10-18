package net.deechael.dcg.source.structure.invokation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DyInvokable {

    void addInvokation(Invokation invokation);

    @NotNull
    List<Invokation> listInvokations();

}
