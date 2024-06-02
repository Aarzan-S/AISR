package com.aisr.initial.presenter;

import com.aisr.initial.model.IModel;
import com.aisr.initial.model.ManagementStaff;
import com.aisr.initial.util.validator.Validator;
import com.aisr.initial.view.IView;

import java.io.IOException;

public class ManagementRegistrationPresenter {
    private IModel<ManagementStaff> managementStaffList;
    private IView<ManagementStaff> view;

    public ManagementRegistrationPresenter(IModel<ManagementStaff> managementStaffList, IView<ManagementStaff> view) {
        this.managementStaffList = managementStaffList;
        this.view = view;
    }

    private boolean isRegistered(String name) {
        return managementStaffList.get(name) != null;
    }

    public void add(ManagementStaff managementStaff) {
        if (!Validator.validatePhoneNumber(managementStaff.getPhoneNumber().toString())) {
            view.display("Phone number is not valid", "ERROR");
            return;
        } else if (!Validator.validateEmail(managementStaff.getEmail())) {
            view.display("Email address is not valid", "ERROR");
            return;
        }
        if (isRegistered(managementStaff.getUsername())) {
            view.display(managementStaff.getUsername() + " is already registered", "ERROR");
            return;
        }
        managementStaffList.add(managementStaff);
        view.clearInputs();
        view.display(managementStaff.getUsername() + " is saved", "INFO");
    }

    public void register() throws IOException {
        if (managementStaffList.getAll().isEmpty()) {
            view.display("Nothing to register", "ERROR");
            return;
        }
        managementStaffList.register();
        view.display("Management Staff registered", "INFO");
    }
}
