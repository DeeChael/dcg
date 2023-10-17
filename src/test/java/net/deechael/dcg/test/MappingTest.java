package net.deechael.dcg.test;

import net.deechael.dcg.DynamicClassManager;
import net.deechael.dcg.map.DynamicMapper;
import net.deechael.dcg.map.Mappings;
import net.deechael.dcg.map.MethodMappings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MappingTest {

    private DynamicClassManager manager;

    @BeforeAll
    public void before() {
        this.manager = DynamicClassManager.builder().build();
    }

    @Test
    public void tryMapping() {
        DynamicMapper mapper = this.manager.mapper();
        HidingClass hiding = new HidingClass();
        MapperClass mapping = mapper.map(hiding, MapperClass.class);
        System.out.println(mapping.getName());
        System.out.println(mapping.getAge());
        mapping.setData("DeeChael", 18);
        System.out.println(mapping.getName());
        System.out.println(mapping.getAge());
    }

}

@Mappings(HidingClass.class)
interface MapperClass {

    @MethodMappings("xasxaxa")
    void setData(String name, int age);

    @MethodMappings("xxxxae12")
    String getName();

    @MethodMappings("seeqqs12345w4saadf")
    int getAge();

}

class HidingClass {

    private String asdadasdad = "asdadxcaweq";
    private int adadadada = 13213;

    private void xasxaxa(String asdax, int asdadasc) {
        this.asdadasdad = asdax;
        this.adadadada = asdadasc;
    }

    public String xxxxae12() {
        return asdadasdad;
    }

    public int seeqqs12345w4saadf() {
        return adadadada;
    }

}