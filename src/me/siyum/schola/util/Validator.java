package me.siyum.schola.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern emailPattern = Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)");
    private static final Pattern numberPattern = Pattern.compile("[0-9]+");
    private static final Pattern stringPattern = Pattern.compile("[a-zA-Z_]+");

    public static boolean isEmailMatch(String text) {
        Matcher emailMatcher = emailPattern.matcher(text);
        return emailMatcher.matches();
    }

    public static boolean isNumberMatch(String text) {
        Matcher salaryMatcher = numberPattern.matcher(text);
        return salaryMatcher.matches();
    }

    public static boolean isString(String text) {
        Matcher nameMatcher = stringPattern.matcher(text);
        return nameMatcher.matches();
    }

    public static boolean validationString(ArrayList<String> data) {
        boolean status = true;
        for (String s : data
        ) {
            status = isString(s);
        }
        return status;
    }

    public static boolean validationInt(ArrayList<Integer> data) {
        boolean status = true;
        for (int s : data
        ) {
            status = isNumberMatch(String.valueOf(s));
        }
        return status;
    }
}
