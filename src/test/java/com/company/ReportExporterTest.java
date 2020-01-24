package com.company;

import com.company.linkResponseInfo.LinkResponseInfo;
import com.company.linkResponseInfo.LinkResponseInfo;
import com.company.reportExporter.ReportExporter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReportExporterTest {
    @Test
    public void Execute_EmptyMap_ExpectedNewFile() {
        /* arrange */
        ArrayList<LinkResponseInfo> links = new ArrayList<>();
        ReportExporter exporter = new ReportExporter();
        /* act */
        exporter.execute( "output.csv", links );
        File file = new File( "output.csv" );
        /* assert */
        assertTrue( file.exists() );
    }

    @Test
    public void Execute_FilledMap_ExpectedFileWhithData() throws IOException {
        /* arrange */
        ArrayList<LinkResponseInfo> links = new ArrayList<>();
        ReportExporter exporter = new ReportExporter();
        links.add( new LinkResponseInfo("localhost:25550", 200) );
        links.add( new LinkResponseInfo("localhost:25050", 404) );
        /* act */
        exporter.execute( "output.csv", links );
        List<String> outputFileLines = Files.readAllLines(Paths.get("output.csv"), StandardCharsets.UTF_8);
        List<String> expectedFileLines = Files.readAllLines(Paths.get("expectedOutput.csv"), StandardCharsets.UTF_8);
        /* assert */
        assertEquals( expectedFileLines, outputFileLines  );
    }
}
