package com.aisr.initial.model;

import com.aisr.initial.Main;
import com.aisr.initial.exception.CustomException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ManagementStaffModel implements IModel<ManagementStaff> {
    private List<ManagementStaff> managementStaffs = new ArrayList<>();
    private List<ManagementStaff> mngts = new ArrayList<>();

    @Override
    public int connect() {
        return 1;
    }

    @Override
    public int disconnect() {
        return 0;
    }

    @Override
    public ManagementStaff get(String username) {
        Optional<ManagementStaff> adminStaff = managementStaffs.stream().filter(mng -> mng.getUsername().equals(username)).findFirst();
        return adminStaff.orElse(null);
    }

    @Override
    public List<ManagementStaff> getAll() {
        return mngts;
    }

    @Override
    public int add(ManagementStaff managementStaff) {
        mngts.add(managementStaff);
        return 1;
    }

    @Override
    public int update(ManagementStaff managementStaff, int index) {
        return 1;
    }

    @Override
    public List<ManagementStaff> loadData() {
        File file = new File("staff.csv");
        if (file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("staff.csv"))) {
                List<ManagementStaff> mngStaffList = bufferedReader.lines().skip(1).map(data -> {
                    String[] staffDetails = data.split(",");
                    return new ManagementStaff(
                            staffDetails[0],
                            staffDetails[1],
                            Long.parseLong(staffDetails[2]),
                            staffDetails[3],
                            staffDetails[4],
                            staffDetails[5],
                            staffDetails[6],
                            staffDetails[7],
                            staffDetails[8]
                    );
                }).collect(Collectors.toList());
                return mngStaffList;
            } catch (IOException ex) {
                System.out.println("Could not load csv file");
                throw new CustomException("Could not load staff file : "+ex.getMessage());
            }
        }
        return Collections.emptyList();
    }

    @Override
    public int register() throws IOException {
        File file = new File("staff.csv");
        if (!file.exists()) {
            file.createNewFile();
            FileWriter writer = new FileWriter("staff.csv", true);
            writer.append("Full Name,Address,Phone Number,Email,Username,Password,Staff ID," +
                    "Position Type,Management Level,Branch");
            writer.append("\n");
            writer.close();
        }
        FileWriter writer = new FileWriter("staff.csv", true);
        for (ManagementStaff mngStaff : mngts) {
            writer.append(mngStaff.toString());
            writer.append("\n");
            Main.noOfEntries++;
        }
        writer.close();
        mngts.clear();
        return 0;
    }
}
