package com.company.reportExporter;

import javenue.csv.Csv;
import java.util.Map;

public class ReportExporter {
    public void execute( String outputFileName, Map<String, Integer> linksStatusCode ) {
        Csv.Writer writer = CreateCSVWriter( outputFileName );
        WriteReport(writer, linksStatusCode);
    }

    private Csv.Writer CreateCSVWriter( String outputFileName ) {
        return new Csv.Writer( outputFileName );
    }

    private void WriteReport( Csv.Writer writer, Map<String, Integer> links ) {
        writer.delimiter( ',' );
        writer.comment( "Example of output: http://xyz.com,404,Not found" );
        links.forEach((link, code) -> writer.value(link).value(code.toString()).newLine());
        writer.close();
    }
}
