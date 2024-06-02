package com.aisr.initial.presenter;

import com.aisr.initial.model.AdminStaff;
import com.aisr.initial.model.IModel;
import com.aisr.initial.util.validator.Validator;
import com.aisr.initial.view.IView;

import java.io.IOException;

public class AdminRegistrationPresenter {
    private IModel<AdminStaff> adminStaffModel;
    private IView<AdminStaff> view;

    public AdminRegistrationPresenter(IModel<AdminStaff> adminStaffModel, IView<AdminStaff> view) {
        this.adminStaffModel = adminStaffModel;
        this.view = view;
    }

    private boolean isRegistered(String name) {
        return adminStaffModel.get(name) != null;
    }

    public void add(AdminStaff adminStaff) {
        if (!Validator.validatePhoneNumber(adminStaff.getPhoneNumber().toString())) {
            view.display("Phone number is not valid", "ERROR");
            return;
        } else if (!Validator.validateEmail(adminStaff.getEmail())) {
            view.display("Email address is not valid", "ERROR");
            return;
        }
        if (isRegistered(adminStaff.getUsername())) {
            view.display(adminStaff.getUsername() + " is already registered", "ERROR");
            return;
        }
        adminStaffModel.add(adminStaff);
        view.clearInputs();
        view.display(adminStaff.getUsername() + " is saved", "INFO");
    }

    public void register() throws IOException {
        if (adminStaffModel.getAll().isEmpty()) {
            view.display("Nothing to register", "ERROR");
            return;
        }
        adminStaffModel.register();
        view.display("Admin Staff registered", "INFO");
    }
}
