package com.aisr.initial.util;

import javafx.scene.control.TextFormatter;

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

