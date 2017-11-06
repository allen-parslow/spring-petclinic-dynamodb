package org.springframework.samples.petclinic.vets.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.samples.petclinic.api.core.ObjectMapperFactory;

import java.io.IOException;
import java.io.InputStream;

public final class FileTestUtils {

    private static final ObjectMapper MAPPER = new ObjectMapperFactory().objectMapper();

    private FileTestUtils(){}

    public static String readFile(String classPath) {
        ClassPathResource resource = new ClassPathResource(classPath);

        try (InputStream io = resource.getInputStream()){
            return IOUtils.toString(io, "utf-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readJson(String classPath, Class<T> dataType) {
        try {
            return MAPPER.readValue(readFile(classPath), dataType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
