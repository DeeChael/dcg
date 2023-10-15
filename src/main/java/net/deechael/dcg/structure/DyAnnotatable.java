package net.deechael.dcg.structure;

import net.deechael.dcg.variable.JvmVariable;
import net.deechael.dcg.DyTranstringable;
import net.deechael.dcg.variable.DyType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface DyAnnotatable extends DyTranstringable {

    /**
     * Add annotation to annotatable object
     *
     * @param annotationType annotation type
     * @param values the key-values of the annotation
     */
    void annotate(@NotNull DyType annotationType, @Nullable Map<String, JvmVariable> values);

    /**
     * List all the annotations annotated on this annotatable object
     *
     * @return annotated annotations
     */
    @NotNull
    Map<DyType, Map<String, JvmVariable>> listAnnotations();

}
