package com.aisr.initial.util;

import com.aisr.initial.model.Recruit;

import java.io.*;
import java.util.ArrayList;
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

    public static String checkForDuplicates(String phoneNumber, String emailAddress, String userName, String fileName) {
        List<String[]> rows;
        if (doesFileExists(fileName)) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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

    public static List<Recruit> fetchRecruitDetails(){
        List<Recruit> recruitList = new ArrayList<>();
        if (doesFileExists(Constants.RECRUIT_CSV_FILE)){
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.RECRUIT_CSV_FILE))) {
                recruitList = bufferedReader.lines().skip(1).map(data -> {
                    String[] recruitDetails = data.split(",");
                    return new Recruit(
                            recruitDetails[0],
                            recruitDetails[1],
                            recruitDetails[2],
                            recruitDetails[3],
                            recruitDetails[4],
                            recruitDetails[5],
                            recruitDetails[6],
                            recruitDetails[7],
                            recruitDetails[8],
                            recruitDetails[9],
                            recruitDetails[10],
                            recruitDetails[11]
                    );
                }).collect(Collectors.toList());
            } catch (IOException e) {
                System.err.println("Could not load recruit data.");
                throw new RuntimeException(e);
            }
        }
        return recruitList;
    }

    public static void updateCSV(Recruit recruit, int index) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.RECRUIT_CSV_FILE))) {
            String line;
            StringBuilder content = new StringBuilder();
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                if (lineNum == index) {
                    line = recruit.toString();
                }
                content.append(line).append("\n");
                lineNum++;
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.RECRUIT_CSV_FILE));
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not update recruit details");
        }
    }
}