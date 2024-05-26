package com.aisr.initial.controller;

import com.aisr.initial.model.Recruit;
import com.aisr.initial.util.Constants;
import com.aisr.initial.util.FileUtil;
import com.aisr.initial.util.NavigationHelper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class handles view recruit logic
 */
public class ViewRecruitController implements Controller {
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

    /**
     * Initialize userName and userRole for this class
     * Loads recruit data from file
     * @param userName name of the logged-in user
     * @param userRole role of the logged-in user e.g. Admin, Management
     */
    @Override
    public void setUp(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
        loadRecruitData();
    }

    /**
     * Loads data from file, creates column definitions and set the columns along with their data.
     */
    public void loadRecruitData() {
        fetchData();
        createColumns();
        tableView.setItems(filteredList);
    }

    /**
     *  Binds the searchBox property to an event listener which hand;les searching
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchBox.textProperty().addListener((obs, oldVal, newVal) -> filteredList.setPredicate(recruit -> {
            if (newVal == null || newVal.isEmpty()) {
                return true;
            }
            return recruit.getFullName().toLowerCase().contains(newVal.toLowerCase().trim());
        }));
    }

    /**
     * Loads Recruit data from file and adds those records into recruits which is List of Observables
     */
    private void fetchData() {
        recruitList = FileUtil.fetchRecruitDetails();
        recruits.addAll(recruitList);
    }

    /**
     * It will create table definition by defining rows for the table
     */
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
                "Adelaide", "Sydney", "Melbourne"), "location"));
        locationColumn.setEditable(false);

        TableColumn<Recruit, Void> buttonColumn = new TableColumn<>("Action");
        buttonColumn.setPrefWidth(100);
        buttonColumn.setSortable(false);
        buttonColumn.setCellFactory(param -> new ButtonCell(userRole));
        tableView.getColumns().addAll(nameColumn, addressColumn, phoneNumberColumn, emailColumn, usernameColumn,
                passwordColumn, interviewDateColumn, highestQualificationColumn, departmentColumn, locationColumn, buttonColumn);
    }

    /**
     * Fetches updated data from file.
     * @param actionEvent
     */
    @FXML
    public void onRefresh(ActionEvent actionEvent) {
        this.recruitList.clear();
        this.tableView.getItems().clear();
        this.fetchData();
        tableView.setItems(filteredList);
        this.showMessage("Refreshed Successfully");
    }

    /**
     * Show success message on UI for 5 seconds then it will get reset to empty string
     */
    private void showMessage(String message) {
        this.infoLabel.setText(message);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5),
                event -> infoLabel.setText("")));
        timeline.setCycleCount(1);
        timeline.play();
    }

    /**
     * This class defines the property of dropdown column in table namely
     *  location and department.
     */
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

    /**
     * This class defines button property for 'Update' button in table.
     * updateCSV method is bind to the click event of the button
     */
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

    /**
     * Updates the table row, and is  bind to 'Update' button in table row
     * @param recruit Recruit Object representing updated row
     * @param index Index of row in the table
     */
    private void updateCSV(Recruit recruit, int index) {
        FileUtil.updateCSV(recruit, index);
        this.showMessage("Recruit Updated Successfully");
        System.out.println("Recruit Updated Successfully");
    }

    /**
     * Navigates to Admin page or Staff page based on the logged-in user
     */
    @FXML
    private void navigateBack() {
        String page = userRole.equals("Admin") ? Constants.ADMIN_PAGE : Constants.MANAGEMENT_PAGE;
        NavigationHelper.navigate("view/" + page, userName, userRole);
    }

}
