module com.aisr.initial {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.aisr.initial to javafx.fxml;
    exports com.aisr.initial;
    exports com.aisr.initial.controller;
    opens com.aisr.initial.controller to javafx.fxml;
    exports com.aisr.initial.model;
    opens com.aisr.initial.model to javafx.fxml;

}