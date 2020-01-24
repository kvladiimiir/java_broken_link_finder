package com.company.reportExporter;

import com.company.linkResponseInfo.LinkResponseInfo;
import javenue.csv.Csv;

import java.util.ArrayList;
import java.util.Map;

public class ReportExporter {
    public void execute( String outputFileName, ArrayList<LinkResponseInfo> linksStatusCode ) {
        Csv.Writer writer = CreateCSVWriter( outputFileName );
        WriteReport(writer, linksStatusCode);
    }

    private Csv.Writer CreateCSVWriter( String outputFileName ) {
        return new Csv.Writer( outputFileName );
    }

    private void WriteReport( Csv.Writer writer, ArrayList<LinkResponseInfo> links ) {
        writer.delimiter( ',' );
        //writer.comment( "Example of output: http://xyz.com,404,Not found" );
        links.forEach((link) -> writer.value(link.getUri()).value(link.getNumberStatusCode().toString()).value(link.getTextStatusCode()).newLine());
        writer.close();
    }
}
