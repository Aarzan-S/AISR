package com.aisr.initial.model;

import com.aisr.initial.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdminStaffModel implements IModel<AdminStaff> {
    private List<AdminStaff> adminStaffs = new ArrayList<>();

    private List<AdminStaff> admins = new ArrayList<>();

    public AdminStaffModel() {
        this.adminStaffs.addAll(loadData());
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
    public AdminStaff get(String username) {
        Optional<AdminStaff> adminStaff = adminStaffs.stream().filter(admin -> admin.getUsername().equals(username)).findFirst();
        return adminStaff.orElse(null);
    }

    @Override
    public List<AdminStaff> getAll() {
        return admins;
    }

    @Override
    public int add(AdminStaff adminStaff) {
        admins.add(adminStaff);
        return 1;
    }

    @Override
    public int update(AdminStaff adminStaff, int index) {
        return 1;
    }

    @Override
    public List<AdminStaff> loadData() {
        File file = new File("staff.csv");
        if (file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("staff.csv"))) {
                List<AdminStaff> adminStaffList = bufferedReader.lines().skip(1).map(data -> {
                    String[] staffDetails = data.split(",");
                    return new AdminStaff(
                            staffDetails[0],
                            staffDetails[1],
                            Long.parseLong(staffDetails[2]),
                            staffDetails[3],
                            staffDetails[4],
                            staffDetails[5],
                            staffDetails[6],
                            staffDetails[7]
                    );
                }).collect(Collectors.toList());
                return adminStaffList;
            } catch (IOException ex) {
                System.out.println("Could not load csv file");
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
        for (AdminStaff adminStaff : admins) {
            writer.append(adminStaff.toString());
            writer.append("\n");
            Main.noOfEntries++;
        }
        writer.close();
        admins.clear();
        return 0;
    }
}
