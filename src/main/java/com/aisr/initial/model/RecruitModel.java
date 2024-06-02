package com.aisr.initial.model;

import com.aisr.initial.Main;
import com.aisr.initial.exception.CustomException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecruitModel implements IModel<Recruit> {
    private List<Recruit> recruitList;
    private List<Recruit> recruits = new ArrayList<>();
    public RecruitModel() {
        recruitList = loadData();
    }

    @Override
    public int connect() {
        return 1;
    }

    @Override
    public int disconnect() {
        return 0;
    }

    @Override
    public Recruit get(String username) {
        Optional<Recruit> recruit = recruitList.stream().filter(r -> r.getUsername().equals(username)).findFirst();
        return recruit.orElse(null);
    }

    @Override
    public List<Recruit> getAll() {
        return recruitList;
    }

    @Override
    public int add(Recruit recruit) {
        recruits.add(recruit);
        return 1;
    }

    @Override
    public int update(Recruit recruit, int index) {
        try (BufferedReader reader = new BufferedReader(new FileReader("recruit.csv"))) {
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
            BufferedWriter writer = new BufferedWriter(new FileWriter("recruit.csv"));
            writer.write(content.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not update recruit details");
        }
        return 1;
    }

    @Override
    public List<Recruit> loadData() {
        File file = new File("recruit.csv");
        if (file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("recruit.csv"))) {
                List<Recruit> recruits = bufferedReader.lines().skip(1).map(data -> {
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
                return recruits;
            } catch (IOException ex) {
                System.out.println("Could not load csv file");
                throw new CustomException("Could not load recruit file : "+ex.getMessage());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public int register() throws IOException {
        File file = new File("recruit.csv");
        if (!file.exists()) {
            file.createNewFile();
            FileWriter writer = new FileWriter("recruit.csv", true);
            writer.append("Full Name,Address,Phone Number,Email,Username,Password,Interview Date," +
                    "Qualification Level,Department,Location,Recruited By,Recruited On");
            writer.append("\n");
            writer.close();
        }
        FileWriter writer = new FileWriter("recruit.csv", true);
        for (Recruit recruit : recruits) {
            writer.append(recruit.toString());
            writer.append("\n");
            Main.noOfEntries++;
        }
        writer.close();
        recruits.clear();
        return 1;
    }
}
