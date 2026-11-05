package day8;

public class FakeReportWriter implements ReportWriter {   // for tests: no real file
    private String lastWritten;
    public void write(String content) {
        this.lastWritten = content;
        System.out.println(content);
    }
    public String getLastWritten() { return lastWritten; }
}