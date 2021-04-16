package com.trainings_notebook.backend;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class TestClass {
    public static void main(String[] args) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        DecimalFormat decimalFormat = new DecimalFormat("0.000", symbols);
        Double testDouble = 0.0d;
        double test2 = 0.055;


        System.out.println(decimalFormat.format(test2));
        System.out.println(decimalFormat.format(testDouble));
        System.out.println("test: " + (10 * 0.055000000000000000277555756156289135105907917022705078125));
    }
}
