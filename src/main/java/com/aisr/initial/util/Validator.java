package com.aisr.initial.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static final String EMAIL_VALIDATION_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
    public static final String PHONE_NO_VALIDATION_REGEX = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    public static boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_VALIDATION_REGEX);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern phoneNoPattern = Pattern.compile(PHONE_NO_VALIDATION_REGEX);
        Matcher matcher = phoneNoPattern.matcher(phoneNumber);
        return matcher.matches();
    }
}