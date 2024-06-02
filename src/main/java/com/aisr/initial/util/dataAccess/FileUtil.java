package com.aisr.initial.util.dataAccess;

import com.aisr.initial.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class contains methods related to file handling
 */
public class FileUtil {
    public static void countNoOfRecords(String fileName) {
        File file = new File("staff.csv");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                Main.noOfEntries = (int) br.lines().count();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}