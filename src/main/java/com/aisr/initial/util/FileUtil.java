package com.aisr.initial.util;

import com.aisr.initial.model.Recruit;
import com.aisr.initial.model.Staff;

import java.io.*;
import java.time.LocalDate;
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

    public static List<Recruit> fetchRecruitDetails() {
        List<Recruit> recruitList = new ArrayList<>();
        if (doesFileExists(Constants.RECRUIT_CSV_FILE)) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.RECRUIT_CSV_FILE))) {
                recruitList = bufferedReader.lines().skip(1).map(data -> {
                    String[] recruitDetails = data.split(",");
                    return new Recruit(
                            recruitDetails[0],
                            recruitDetails[1],
                            Long.parseLong(recruitDetails[2]),
                            recruitDetails[3],
                            recruitDetails[4],
                            recruitDetails[5],
                            LocalDate.parse(recruitDetails[6]),
                            recruitDetails[7],
                            recruitDetails[8],
                            recruitDetails[9],
                            recruitDetails[10],
                            LocalDate.parse(recruitDetails[11])
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
        System.out.println("recruit : "+ recruit.toString());
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

    public static void addStaffData(String fileName, List<? extends Staff> staffList) throws Exception {
        if (!doesFileExists(fileName)) {
            new File(fileName).createNewFile();
            FileWriter writer = new FileWriter(fileName, true);
            writer.append(fileName.equals(Constants.STAFF_CSV_FILE) ? Constants.STAFF_CSV_HEADER : Constants.RECRUIT_CSV_HEADER);
            writer.append("\n");
            writer.close();
        }
        try (FileWriter writer = new FileWriter(fileName, true)) {
            for (Staff staff : staffList) {
                System.out.println(staff.toString());
                writer.append(staff.toString()).append("\n");
                Constants.noOfEntries++;
            }
        } catch (IOException e) {
            System.err.println("Could not write Staff data: " + e.getMessage());
            throw new Exception("Could not write Staff data: " + e.getMessage());
        }
    }

    public static void addRecruitData(List<Recruit> recruitList) throws Exception {
        if (!doesFileExists(Constants.RECRUIT_CSV_FILE)) {
            new File(Constants.RECRUIT_CSV_FILE).createNewFile();
            FileWriter writer = new FileWriter(Constants.RECRUIT_CSV_FILE, true);
            writer.append(Constants.RECRUIT_CSV_HEADER);
            writer.append("\n");
            writer.close();
        }
        try (FileWriter writer = new FileWriter(Constants.RECRUIT_CSV_FILE, true)) {
            for (Recruit recruitData : recruitList) {
                writer.append(recruitData.toString());
                writer.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Could not write Recruit data: " + e.getMessage());
            throw new Exception("Could not write Recruit data: " + e.getMessage());
        }
    }
}