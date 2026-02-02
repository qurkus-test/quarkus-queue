package com.gaspo.quarkustest.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class FileUtils {

    public static Optional<String> readFileFromResourceToString(String resourcePath) {
        //Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(resourcePath);
             InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return Optional.of(new BufferedReader(reader).lines().collect(Collectors.joining("\n")));
        } catch (IOException e) {
            log.error("Error reading the file: " + e.getMessage());
            return Optional.empty();
        }
    }
}
