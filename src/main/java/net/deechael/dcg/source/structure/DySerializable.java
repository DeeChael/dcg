package net.deechael.dcg.source.structure;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public interface DySerializable {

    /**
     * To serialize the object to a json object
     * @return serialized json object
     */
    @NotNull
    JsonObject serialize();

}
