package net.deechael.dcg.source.structure.execution;

import net.deechael.dcg.source.structure.DyLabel;
import net.deechael.dcg.source.structure.DyStructure;
import net.deechael.dcg.source.structure.invokation.internal.BreakInvokation;
import org.jetbrains.annotations.Nullable;

public class DySwitchExecutable extends DyInnerExecutable {

    public DySwitchExecutable(DyStructure[] parentDomains) {
        super(parentDomains);
    }

    public void doBreak() {
        this.doBreak(null);
    }

    public void doBreak(@Nullable DyLabel label) {
        this.addInvokation(new BreakInvokation(label));
    }

}
