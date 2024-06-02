package com.aisr.initial.presenter;

import com.aisr.initial.model.IModel;
import com.aisr.initial.model.Staff;
import com.aisr.initial.view.IView;

public class LoginPresenter {
    private IModel<Staff> staffIModel;
    private IView<Staff> view;

    public LoginPresenter(IModel<Staff> staffIModel, IView<Staff> view) {
        this.staffIModel = staffIModel;
        this.view = view;
    }

    private boolean isValidUserName(String username) {
        return staffIModel.get(username) != null;
    }

    private boolean validatePassword(Staff staff) {
        if (staffIModel.get(staff.getUsername()).getPassword().equals(staff.getPassword())) {
            staff.setStaffId(staff.getUsername().equals("superadmin") ? "Staff" : staffIModel.get(staff.getUsername()).getStaffId());
            return true;
        }
        return false;
    }

    public boolean login(Staff staff) {
        if (!isValidUserName(staff.getUsername())) {
            view.display("Invalid username", "ERROR");
            return false;
        }
        if (!validatePassword(staff)) {
            view.display("Invalid password", "ERROR");
            return false;
        }
        return true;
    }
}
