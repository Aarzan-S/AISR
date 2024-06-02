package com.aisr.initial.presenter;

import com.aisr.initial.model.IModel;
import com.aisr.initial.model.ManagementStaff;
import com.aisr.initial.view.IView;

public class ManagementPresenter {
    private IModel<ManagementStaff> managementStaffList;
    private IView<ManagementStaff> view;

    public ManagementPresenter(IModel<ManagementStaff> managementStaffList, IView<ManagementStaff> view) {
        this.managementStaffList = managementStaffList;
        this.view = view;
    }
}
