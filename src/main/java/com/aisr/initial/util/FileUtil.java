package com.aisr.initial.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
    public static boolean doesFileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static void countNoOfRecords(String fileName) {
        if (doesFileExists(fileName)) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                Constants.noOfEntries = (int) br.lines().count();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String checkForDuplicates(String phoneNumber, String emailAddress, String userName) {
        List<String[]> rows;
        if (doesFileExists(Constants.STAFF_CSV_FILE)) {
            try (BufferedReader br = new BufferedReader(new FileReader(Constants.STAFF_CSV_FILE))) {
                rows = br.lines().skip(1).map(row -> row.split(",")).collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (rows.stream().anyMatch(data -> phoneNumber.equals(data[3]))) {
                return "Phone Number already used";
            } else if (rows.stream().anyMatch(data -> emailAddress.equals(data[4]))) {
                return "Email Address already used";
            } else if (rows.stream().anyMatch(data -> userName.equals(data[5]))) {
                return "Username already used";
            }
        }
        return "";
    }
}