package com.aisr.initial.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StaffModel implements IModel<Staff> {
    private List<Staff> staffs = new ArrayList<>();

    public StaffModel() {
        this.staffs.add(new Staff("SUPERADMIN", "", 11111111111L, "superadmin@aisr.com",
                "superadmin", "superadmin", "STAFF0"));
        staffs.addAll(loadData());
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
    public Staff get(String username) {
        Optional<Staff> staff = staffs.stream().filter(s -> s.getUsername().equals(username)).findFirst();
        return staff.orElse(null);
    }

    @Override
    public List<Staff> getAll() {
        return staffs;
    }

    @Override
    public int add(Staff staff) {
        return 1;
    }

    @Override
    public int update(Staff staff, int index) {
        return 1;
    }

    @Override
    public List<Staff> loadData() {
        File file = new File("staff.csv");
        if (file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("staff.csv"))) {
                List<Staff> staffList = bufferedReader.lines().skip(1).map(data -> {
                    String[] staffDetails = data.split(",");
                    return new Staff(
                            staffDetails[4],
                            staffDetails[5],
                            staffDetails[6]
                    );
                }).collect(Collectors.toList());
                return staffList;
            } catch (IOException ex) {
                System.out.println("Could not load csv file");
            }
        }
        return Collections.emptyList();
    }

    @Override
    public int register() {
        return 0;
    }
}
