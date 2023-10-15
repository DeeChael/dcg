package net.deechael.dcg.test;

import net.deechael.dcg.DynamicClassManager;
import net.deechael.dcg.compiler.DynamicCompiler;
import net.deechael.dcg.sourcer.DyClass;
import net.deechael.dcg.sourcer.DynamicSourcer;
import net.deechael.dcg.variable.Visibility;
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
        DyClass clazz = sourcer.newClass()
                .withPackage("net.deechael.dcg.test")
                .withName("Test")
                .withVisibility(Visibility.PUBLIC)
                .build();

    }

}
