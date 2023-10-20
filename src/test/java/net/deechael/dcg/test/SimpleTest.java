package net.deechael.dcg.test;

import net.deechael.dcg.DynamicClassManager;
import net.deechael.dcg.compile.DynamicCompiler;
import net.deechael.dcg.source.DyClass;
import net.deechael.dcg.source.DyPackage;
import net.deechael.dcg.source.DynamicSourcer;
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

    }

}
