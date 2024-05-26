package com.aisr.initial.controller;

import com.aisr.initial.model.Recruit;
import com.aisr.initial.util.Constants;
import com.aisr.initial.util.FileUtil;
import com.aisr.initial.util.NavigationHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryController implements Controller {
    String userName;
    String userRole;
    List<Recruit> recruitList = new ArrayList<>();
    ObservableList<Recruit> recruits = FXCollections.observableArrayList();
    FilteredList<Recruit> filteredList = new FilteredList<>(recruits);

    @FXML
    TableView<Recruit> tableView = new TableView<>();
    @FXML
    Label infoLabel;
    @FXML
    TextField searchBox;

    @Override
    public void setUp(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
        loadRecruitData();
    }

    public void loadRecruitData() {
        fetchData();
        createColumns();
        tableView.setItems(filteredList);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchBox.textProperty().addListener((obs, oldVal, newVal) -> filteredList.setPredicate(recruit -> {
            if (newVal == null || newVal.isEmpty()) {
                return true;
            }
            return recruit.getFullName().toLowerCase().contains(newVal.toLowerCase().trim());
        }));
    }

    private void fetchData() {
        recruitList = FileUtil.fetchRecruitHistoryDetails();
        recruits.addAll(recruitList);
    }

    private void createColumns() {
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

    @FXML
    private void navigateBack() {
        String page = userRole.equals("Admin") ? Constants.ADMIN_PAGE : Constants.STAFF_PAGE;
        NavigationHelper.navigate("view/" + page, userName, userRole);
    }

}
