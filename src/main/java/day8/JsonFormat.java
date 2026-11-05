package day8;

import java.util.List;

public class JsonFormat implements  Format{
    @Override
    public String output(List<String> rows) {
        return "[\"" + String.join("\",\"", rows) + "\"]";
    }
}
