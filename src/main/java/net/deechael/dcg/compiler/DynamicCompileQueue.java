package net.deechael.dcg.compiler;

import net.deechael.dcg.structure.DyGeneratable;

import java.util.ArrayList;
import java.util.List;

public class DynamicCompileQueue {

    private final DynamicCompiler compiler;
    private final List<DyGeneratable> generatables = new ArrayList<>();

    public DynamicCompileQueue(DynamicCompiler dynamicCompiler) {
        this.compiler = dynamicCompiler;
    }

    /**
     * Add generatable to be compiled to the queue
     *
     * @param generatable object to be compiled
     * @return this
     */
    public DynamicCompileQueue join(DyGeneratable generatable) {
        return this;
    }

    /**
     * Prepare to compile all the generatable in this queue
     *
     * @return compile task
     */
    public DynamicCompileTask ready() {
        return new DynamicCompileTask(this.compiler, this.generatables);
    }

}
