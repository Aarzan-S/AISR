package com.aisr.initial.util.validator;

import javafx.scene.control.TextFormatter;

/*
    this class will validate the text field to be strictly numeric
    having length 10. This class can be used in Phone number field validation
 */
public class NumberFieldValidator extends TextFormatter<String> {
    public NumberFieldValidator() {
        super(change -> {
            if (change.getControlNewText().matches("\\d*")
                    && change.getControlNewText().matches(".{0,10}")) {
                return change;
            } else {
                return null;
            }
        });
    }
}

