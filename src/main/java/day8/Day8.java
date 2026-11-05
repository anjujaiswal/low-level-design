package day8;

import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        ReportWriter writer1 = new FileReportWriter();
        ReportService1 service1 = new ReportService1(writer1);
        Format json = new JsonFormat();
        Format xml = new XmlFormat();
//        service1.generateOutput(json, List.of("anjj","isha"));
        ReportWriter writer2 = new FakeReportWriter();

        ReportService1 service2 = new ReportService1(writer2);
        service2.generateOutput(xml,  List.of("anjj","isha32r2r"));
    }
}
