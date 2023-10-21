package net.deechael.dcg.source.structure.execution;

import net.deechael.dcg.source.structure.DyLabel;
import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.invokation.internal.ContinueInvokation;
import org.jetbrains.annotations.Nullable;

public final class DyLoopExecutable extends DySwitchExecutable {

    public DyLoopExecutable(DyStructure[] parentDomains) {
        super(parentDomains);
    }

    public void doContinue() {
        this.doBreak(null);
    }

    public void doContinue(@Nullable DyLabel label) {
        this.addInvokation(new ContinueInvokation(label));
    }

}
