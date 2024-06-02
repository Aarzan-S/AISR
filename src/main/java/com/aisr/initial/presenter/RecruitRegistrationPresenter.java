package com.aisr.initial.presenter;

import com.aisr.initial.model.IModel;
import com.aisr.initial.model.Recruit;
import com.aisr.initial.util.validator.Validator;
import com.aisr.initial.view.IView;

import java.io.IOException;

public class RecruitRegistrationPresenter {
    private IModel<Recruit> recruitModel;
    private IView<Recruit> view;

    public RecruitRegistrationPresenter(IModel<Recruit> recruitModel, IView<Recruit> view) {
        this.recruitModel = recruitModel;
        this.view = view;
    }

    private boolean isRegistered(String name) {
        return recruitModel.get(name) != null;
    }

    public void add(Recruit recruit) {
        if (!Validator.validatePhoneNumber(recruit.getPhoneNumber().toString())) {
            view.display("Phone number is not valid", "ERROR");
            return;
        } else if (!Validator.validateEmail(recruit.getEmail())) {
            view.display("Email address is not valid", "ERROR");
            return;
        }
        if (isRegistered(recruit.getUsername())) {
            view.display(recruit.getUsername() + " is already registered", "ERROR");
            return;
        }
        recruitModel.add(recruit);
        view.clearInputs();
        view.display(recruit.getUsername() + " is saved", "INFO");
    }

    public void register() throws IOException {
        if (recruitModel.getAll().isEmpty()) {
            view.display("Nothing to register", "ERROR");
            return;
        }
        recruitModel.register();
        view.display("Recruit registered", "INFO");
    }
}
