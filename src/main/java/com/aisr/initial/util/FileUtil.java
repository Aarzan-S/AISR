package com.aisr.initial.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
    public static boolean doesFileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static void countNoOfRecords(String fileName) {
        if (doesFileExists(fileName)){
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                Constants.noOfEntries = (int) br.lines().count();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Constants.noOfEntries = 1;
    }

}
