package com.aisr.initial.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * THis class has methods that validates email and password fields
 */
public class Validator {
    /**
     * Checks whether provided email is valid or not
     * @param email
     * @return true is emila is valid else false
     */
    public static boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile(Constants.EMAIL_VALIDATION_REGEX);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Checks whether provided phoneNumber is valid or not
     * @param phoneNumber
     * @return true is phone number is valid else false
     */
    public static boolean validatePhoneNumber(String phoneNumber) {
        Pattern phoneNoPattern = Pattern.compile(Constants.PHONE_NO_VALIDATION_REGEX);
        Matcher matcher = phoneNoPattern.matcher(phoneNumber);
        return matcher.matches();
    }
}