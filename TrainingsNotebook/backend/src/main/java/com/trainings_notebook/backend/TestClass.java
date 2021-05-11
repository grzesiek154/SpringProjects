package com.trainings_notebook.backend;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Instant;
import java.util.regex.Pattern;

public class TestClass {
    public static void main(String[] args) {
        String dateNow = Instant.now().toString();
        Long dateNow2 = Instant.now().toEpochMilli();

        Pattern dataPattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        StringBuilder sb = new StringBuilder(dateNow);
        String newDateFormat = sb.replace(10,11," ").delete(19, dateNow.length()).toString();


        System.out.println(dateNow);
        System.out.println(newDateFormat);
        System.out.println(dateNow2);
    }



    public interface DataMatcher {
        boolean matches(String date);
    }
}
