package dev.israel.utilities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {
    public static void logInfo(String message, LogLevel level) {
        // LOG LEVEL + message + TimeStamp
        String logMessage = level.name() + " " + message + " " + new Date() + " \n";

        try {
            Files.write(Paths.get("/Users/lamechisrael/LamechProject1/project1.log"),
                    logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
