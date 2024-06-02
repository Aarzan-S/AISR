package com.aisr.initial.presenter;

import com.aisr.initial.model.IModel;
import com.aisr.initial.model.Recruit;
import com.aisr.initial.view.IView;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RecruitHistoryPresenter {
    private IModel<Recruit> recruitModel;
    private IView<Recruit> view;

    public RecruitHistoryPresenter(IModel<Recruit> recruitModel, IView<Recruit> view) {
        this.recruitModel = recruitModel;
        this.view = view;
    }

    public List<Recruit> loadData() {
        return recruitModel.getAll();
    }

    public void generateColumnDefinition(TableView<Recruit> tableView, String userRole) {
        tableView.setEditable(false);
        TableColumn<Recruit, String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<Recruit, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Recruit, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Recruit, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Recruit, String> recruitedByColumn = new TableColumn<>("Recruited By");
        recruitedByColumn.setCellValueFactory(new PropertyValueFactory<>("recruitedBy"));

        TableColumn<Recruit, String> recruitedOnColumn = new TableColumn<>("Recruited On");
        recruitedOnColumn.setCellValueFactory(new PropertyValueFactory<>("recruitedOn"));

        tableView.getColumns().addAll(nameColumn, phoneNumberColumn, emailColumn, usernameColumn, recruitedByColumn,
                recruitedOnColumn);
    }

}
