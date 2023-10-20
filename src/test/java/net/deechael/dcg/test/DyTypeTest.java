package net.deechael.dcg.test;

import net.deechael.dcg.source.variable.DyType;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DyTypeTest {

    @Test
    public void arrayTest() {
        System.out.println(DyType.classType(String[][][][].class).toTypeString());
    }

    @Test
    public void genericTest() {
        System.out.println(DyType.genericClassType(List[][][].class, DyType.classType(String[][].class)).toTypeString());
    }

}
