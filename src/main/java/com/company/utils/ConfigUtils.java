package com.company.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    public static Integer getNumberOfThreads() throws IOException {
        InputStream input = new FileInputStream("F:\\Java_course\\java_broken_link_finder\\config.properties");

        Properties prop = new Properties();
        prop.load(input);
        return Integer.valueOf(prop.getProperty("threadsCount"));
    }
}
