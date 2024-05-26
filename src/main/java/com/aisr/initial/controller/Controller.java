package com.aisr.initial.controller;

import javafx.fxml.Initializable;

/**
 * This interface is base for all controllers
 */
public interface Controller extends Initializable {
    /**
     * Defines initialization task for the controller
     * For eg. setting up user info and welcome message
     * @param userName
     * @param userRole
     */
    void setUp(String userName, String userRole);
}
