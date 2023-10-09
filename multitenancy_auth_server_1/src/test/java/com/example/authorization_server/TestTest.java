package com.example.authorization_server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestTest {

    @Test
    void test() throws IOException {
        var b = Files.readAllBytes(
                Path.of(this.getClass().getClassLoader().getResource("test.json").getPath())
        );

        System.out.println(new String(b));
    }
}
