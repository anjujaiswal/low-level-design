package day8;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReportService {
    public String generate(String format, List<String> rows) {
        String output;
        if (format.equals("CSV")) {
            output = String.join(",", rows);
        } else if (format.equals("JSON")) {
            output = "[\"" + String.join("\",\"", rows) + "\"]";
        } else {
            throw new IllegalArgumentException("Unknown format");
        }
        try {
            Files.writeString(Path.of("report.txt"), output);   // saving to disk
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        System.out.println("Report generated");                  // logging
        return output;
    }
}