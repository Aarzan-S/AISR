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

    public static int countNoOfRecords(String fileName) {
        if (doesFileExists(fileName)){
            try (BufferedReader br = new BufferedReader(new FileReader("staff.csv"))) {
                return (int) br.lines().count();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }

}
