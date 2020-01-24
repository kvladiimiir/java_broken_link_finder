package com.company.reportExporter;

import com.company.linkResponseInfo.LinkResponseInfo;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportExporter {
    public void execute(String outputFileName, ArrayList<LinkResponseInfo> links) {
        try {
            CSVWriter writer = CreateCSVWriter(outputFileName);
            WriteReport(writer, links);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CSVWriter CreateCSVWriter( String outputFileName ) throws IOException {
        File file = new File( outputFileName );
        FileWriter outputFile = new FileWriter( file );

        return new CSVWriter( outputFile, ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END );
    }

    private void WriteReport( CSVWriter writer, ArrayList<LinkResponseInfo> links ) throws IOException {
        for (var i: links) {
            String[] line = { i.getUri(), i.getNumberStatusCode().toString(), i.getTextStatusCode() };
            writer.writeNext( line );
        }

        writer.close();
    }
}
