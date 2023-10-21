package net.deechael.dcg.test;

import net.deechael.dcg.DynamicClassManager;
import net.deechael.dcg.compile.DynamicCompiler;
import net.deechael.dcg.source.structure.DyParameter;
import net.deechael.dcg.source.structure.method.DyClassMethod;
import net.deechael.dcg.source.structure.method.DyMethod;
import net.deechael.dcg.source.structure.type.DyClass;
import net.deechael.dcg.source.DyPackage;
import net.deechael.dcg.source.DynamicSourcer;
import net.deechael.dcg.source.type.DyType;
import net.deechael.dcg.source.variable.Variable;
import net.deechael.dcg.source.variable.Visibility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleTest {

    private DynamicClassManager manager;

    @BeforeAll
    public void before() {
        this.manager = DynamicClassManager.builder().build();
    }

    @Test
    public void generate() {
        DynamicCompiler compiler = this.manager.defaultCompiler();
        compiler.prepare()
                .ready()
                .compile();
    }

    @Test
    public void simpleClassTest() {
        DynamicSourcer sourcer = this.manager.sourcer();
        DyPackage pkg = sourcer.newPackage("net.deechael.dcg.test");
        DyClass clazz = pkg.newClass(Visibility.PUBLIC, "Test");
        DyClassMethod method = clazz.newMethod(Visibility.PUBLIC, null, "test");
        DyParameter name = method.addParameter(DyType.STRING, "name");
        method.ifElse()
                .doIf(null /* To be replaced */, executable -> {
                    Variable stdOut = null; /* TODO: Get field */
                    executable.invoke(stdOut, "println", name);
                })
                .doElse(executable -> {

                });
        method.switchCase(name)
                .doCase(Variable.stringVariable("DeeChael"), executable -> {
                    // do sth
                })
                .doCase(
                        new Variable[] {
                                Variable.stringVariable("Gerry"),
                                Variable.stringVariable("Nostal"),
                        },
                        executable -> {
                            // do sth
                        }
                );
        method.invoke(name, "startsWith", Variable.stringVariable("Deerio"));

    }

}
