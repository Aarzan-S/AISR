package com.aisr.initial.controller;

import com.aisr.initial.model.IModel;
import com.aisr.initial.model.Recruit;
import com.aisr.initial.model.RecruitModel;
import com.aisr.initial.presenter.RecruitViewPresenter;
import com.aisr.initial.util.routing.NavigationHelper;
import com.aisr.initial.view.IView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles view recruit logic
 */
public class ViewRecruitController implements Controller, IView<Recruit> {
    public static final String ADMIN_PAGE = "Admin.fxml";
    public static final String MANAGEMENT_PAGE = "Management.fxml";
    private RecruitViewPresenter presenter;
    private String userName;
    private String userRole;
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
        recruits.addAll(presenter.loadData());
        presenter.generateColumnDefinition(tableView, userRole);
        tableView.setItems(filteredList);
    }

    /**
     *  Binds the searchBox property to an event listener which hand;les searching
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IModel<Recruit> model = new RecruitModel();
        presenter = new RecruitViewPresenter(model, this);
        model.connect();
        searchBox.textProperty().addListener((obs, oldVal, newVal) -> filteredList.setPredicate(recruit -> {
            if (newVal == null || newVal.isEmpty()) {
                return true;
            }
            return recruit.getFullName().toLowerCase().contains(newVal.toLowerCase().trim());
        }));
    }

    @Override
    public void display(Recruit object) {

    }

    @Override
    public void display(String message, String type) {
        if ("INFO".equals(type)) {
            infoLabel.setStyle("-fx-text-fill: green;");
            infoLabel.setText(message);
        } else {
            infoLabel.setStyle("-fx-text-fill: red;");
            infoLabel.setText(message);
        }
    }

    @Override
    public void clearInputs() {

    }

    /**
     * Navigates to Admin page or Staff page based on the logged-in user
     */
    @FXML
    private void navigateBack() {
        String page = userRole.equals("Admin") ? ADMIN_PAGE : MANAGEMENT_PAGE;
        NavigationHelper.navigate("view/" + page, userName, userRole);
    }

}
