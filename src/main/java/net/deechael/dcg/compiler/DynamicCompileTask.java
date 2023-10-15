package net.deechael.dcg.compiler;

import net.deechael.dcg.structure.DyGeneratable;

import java.util.List;
import java.util.Map;

public class DynamicCompileTask {

    private final DynamicCompiler compiler;
    private final List<DyGeneratable> generatables;

    public DynamicCompileTask(DynamicCompiler compiler, List<DyGeneratable> generatables) {
        this.compiler = compiler;
        this.generatables = generatables;
    }

    /**
     * Compile all the generatables to jvm classes
     *
     * @return original generatables with their compiled jvm classes
     */
    public Map<DyGeneratable, Class<?>> compile() {
        return this.compiler.generate(this.generatables);
    }

}
