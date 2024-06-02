package com.aisr.initial.presenter;

import com.aisr.initial.model.IModel;
import com.aisr.initial.model.Staff;
import com.aisr.initial.view.IView;

public class StaffPresenter {
    private IModel<Staff> staffModel;
    private IView<Staff> view;

    public StaffPresenter(IModel<Staff> staffModel, IView<Staff> view) {
        this.staffModel = staffModel;
        this.view = view;
    }
}
