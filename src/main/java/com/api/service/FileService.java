package com.api.service;

import com.api.util.StreamGobbler;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

@Service
public class FileService {

    public Boolean executeShell() throws IOException {

        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            System.out.println(System.getProperty("user.dir"));
            builder.command("D:\\Google_Drive_Rahul\\java_program\\BigData\\LearningJava\\scripts\\echo.bat");
        } else {
            builder.command("sh", "-c", "sh /somescript_path/dummyscript.sh 2024 123 125");
        }

        builder.directory(new File(System.getProperty("user.home")));
        Process process = builder.start();

        long pid = process.pid();
        System.out.println("Process ID: " + pid);

        StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), System.err::println);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> outFuture = executorService.submit(outputGobbler);
        Future<?> errorFuture = executorService.submit(errorGobbler);

        int exitCode = 0;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        System.out.println("CPU Load: " + osBean.getProcessCpuLoad());
        System.out.println("Memory Usage: " + osBean.getTotalPhysicalMemorySize());

        if (exitCode != 0) {
            System.out.println("Script execution failed");
            return true;
        } else {
            System.out.println(exitCode);
            return false;
        }


    }

    public static String writeToFile(String filePath,String code) throws IOException {
        Path path = Paths.get(filePath);
        Files.writeString(path, code, StandardCharsets.UTF_8);
        return "";
    }

}
