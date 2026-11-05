package day8;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReportService1 {
    private final ReportWriter writer;

    public ReportService1(ReportWriter writer) {
        if(writer == null){
            throw new IllegalArgumentException("Writer is required");
        }
        this.writer = writer;
    }

    public String generateOutput(Format format, List<String> rows) {
        if(format == null){
            throw new IllegalArgumentException("“Format is required");
        }
        String output;
        output = format.output(rows);
        writer.write(output);
        return output;

    }


}