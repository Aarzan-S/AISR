module com.aisr.initial {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.aisr.initial to javafx.fxml;
    exports com.aisr.initial;
}