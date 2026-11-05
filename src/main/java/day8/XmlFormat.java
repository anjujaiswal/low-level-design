package day8;

import java.util.List;

public class XmlFormat implements Format{
    @Override
    public String output(List<String> rows) {
        StringBuilder xml = new StringBuilder("<rows>");
        for (String row : rows) {
            xml.append("<row>").append(escape(row)).append("</row>");
        }
        xml.append("</rows>");
        return xml.toString();
    }

    // XML treats these characters as markup, so they must be replaced inside values
    private String escape(String value) {
        return value.replace("&", "&amp;")      // & first, or it would re-escape the others
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
