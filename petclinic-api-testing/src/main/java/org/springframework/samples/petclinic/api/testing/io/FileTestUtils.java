package org.springframework.samples.petclinic.api.testing.io;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public final class FileTestUtils {

    private FileTestUtils(){}

    public static String readFile(String classPath) {
        ClassPathResource resource = new ClassPathResource(classPath);

        try (InputStream io = resource.getInputStream()){
            return IOUtils.toString(io, "utf-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
