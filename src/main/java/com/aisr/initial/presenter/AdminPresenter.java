package com.aisr.initial.presenter;

import com.aisr.initial.model.AdminStaff;
import com.aisr.initial.model.IModel;
import com.aisr.initial.view.IView;

public class AdminPresenter {
    private IModel<AdminStaff> adminStaffIModel;
    private IView<AdminStaff> view;

    public AdminPresenter(IModel<AdminStaff> adminStaffIModel, IView<AdminStaff> view) {
        this.adminStaffIModel = adminStaffIModel;
        this.view = view;
    }

}
