package org.springframework.samples.petclinic.api.testing.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.samples.petclinic.api.core.json.ObjectMapperFactory;

import java.io.IOException;

import static org.springframework.samples.petclinic.api.testing.io.FileTestUtils.readFile;

public final class JsonTestUtils {

    private static final ObjectMapper MAPPER = new ObjectMapperFactory().objectMapper();

    private JsonTestUtils(){}

    public static <T> T readJson(String classPath, Class<T> dataType) {
        try {
            return MAPPER.readValue(readFile(classPath), dataType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
