package com.aisr.initial.controller;

import com.aisr.initial.model.Recruit;
import com.aisr.initial.util.FileUtil;
import com.aisr.initial.util.NavigationHelper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewRecruitController implements Initializable {
    String userName;
    String userRole;
    List<Recruit> recruitList = new ArrayList<>();
    @FXML
    TableView<Recruit> tableView = new TableView<>();
    @FXML
    Label infoLabel;

    public void setUserInfo(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void init() {
        fetchData();
        createColumns();
        tableView.getItems().addAll(recruitList);
    }

    private void fetchData() {
        recruitList = FileUtil.fetchRecruitDetails();
        recruitList.forEach(System.out::println);
    }

    private void createColumns() {
        tableView.setEditable(false);
        TableColumn<Recruit, String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<Recruit, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Recruit, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Recruit, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Recruit, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Recruit, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Recruit, String> interviewDateColumn = new TableColumn<>("Interview Date");
        interviewDateColumn.setCellValueFactory(new PropertyValueFactory<>("interviewDate"));

        TableColumn<Recruit, String> highestQualificationColumn = new TableColumn<>("Highest Qualification");
        highestQualificationColumn.setCellValueFactory(new PropertyValueFactory<>("highestQualification"));

        TableColumn<Recruit, String> departmentColumn = new TableColumn<>("Department");
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        departmentColumn.setPrefWidth(100);
        departmentColumn.setCellFactory(column -> new ComboBoxCell(FXCollections.observableArrayList("None",
                "Software", "Aerospace", "Mechanical", "Electronics"), "department"));
        departmentColumn.setEditable(!userRole.equals("Admin"));

        TableColumn<Recruit, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationColumn.setPrefWidth(100);
        locationColumn.setCellFactory(col -> new ComboBoxCell(FXCollections.observableArrayList("None", "Brisbane",
                "Adelaide", "Sydney", "Melbourne"),"location"));
        locationColumn.setEditable(false);

        TableColumn<Recruit, Void> buttonColumn = new TableColumn<>("Action");
        buttonColumn.setPrefWidth(100);
        buttonColumn.setSortable(false);
        buttonColumn.setCellFactory(param -> new ButtonCell(userRole));
        tableView.getColumns().addAll(nameColumn, addressColumn, phoneNumberColumn, emailColumn, usernameColumn,
                passwordColumn, interviewDateColumn, highestQualificationColumn, departmentColumn, locationColumn, buttonColumn);
    }

    public void onRefresh(ActionEvent actionEvent) {
        this.recruitList.clear();
        this.tableView.getItems().clear();
        this.fetchData();
        tableView.getItems().addAll(recruitList);
        this.showMessage("Refreshed Successfully");
    }

    private void showMessage(String message) {
        this.infoLabel.setText(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5),
                event -> infoLabel.setText("")));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private class ComboBoxCell extends TableCell<Recruit, String> {
        private final ComboBox<String> comboBox;

        public ComboBoxCell(ObservableList<String> items, String columnName) {
            this.comboBox = new ComboBox<>(items);
            comboBox.setOnAction(event -> {
                Recruit recruit = getTableRow().getItem();
                if (recruit != null) {
                    if (columnName.equals("department"))
                        recruit.setDepartment(comboBox.getValue());
                    else
                        recruit.setLocation(comboBox.getValue());
                }
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || getTableRow() == null) {
                setGraphic(null);
            } else {
                comboBox.setValue(item);
                setGraphic(comboBox);
            }
        }
    }

    private class ButtonCell extends TableCell<Recruit, Void> {
        private final Button updateButton;
        private final String userRole;

        public ButtonCell(String userRole) {
            updateButton = new Button("Update");
            this.userRole = userRole;
            updateButton.setOnAction(event -> {
                Recruit recruit = getTableView().getItems().get(getIndex());
                updateCSV(recruit, getIndex() + 1);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                updateButton.setDisable(userRole.equals("Admin"));
                setGraphic(updateButton);
            }
        }
    }

    private void updateCSV(Recruit recruit, int index) {
        FileUtil.updateCSV(recruit, index);
        this.showMessage("Recruit Updated Successfully");
        System.out.println("Recruit Updated Successfully");
    }

    @FXML
    private void navigateBack(ActionEvent event) {
        NavigationHelper.navigate(event, userRole, userName);
    }

}
