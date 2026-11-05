package day8;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReportWriter implements ReportWriter{
    @Override
    public void write(String content) {
        try {
            Files.writeString(Path.of("report.txt"), content);   // saving to disk
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
