package com.api.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UtilFunction {
    public static String writeToFile(String filePath,String code) throws IOException {
        Path path = Paths.get(filePath);
        Files.writeString(path, code,StandardCharsets.UTF_8);
        return "";
    }


}
