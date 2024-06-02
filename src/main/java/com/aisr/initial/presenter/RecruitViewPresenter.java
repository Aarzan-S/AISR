package com.aisr.initial.presenter;

import com.aisr.initial.model.IModel;
import com.aisr.initial.model.Recruit;
import com.aisr.initial.view.IView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RecruitViewPresenter {
    private IModel<Recruit> recruitModel;
    private IView<Recruit> view;

    public RecruitViewPresenter(IModel<Recruit> recruitModel, IView<Recruit> view) {
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
     * This class defines the property of dropdown column in table namely
     * location and department.
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
     *
     * @param recruit Recruit Object representing updated row
     * @param index   Index of row in the table
     */
    private void updateCSV(Recruit recruit, int index) {
        recruitModel.update(recruit, index);
        view.display("Recruit Updated Successfully", "INFO");
    }

}
