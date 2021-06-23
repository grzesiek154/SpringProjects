package com.trainings_notebook.backend;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;


public class TestClass2 {



    public static void main(String[] args) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(Date.from(Instant.now()));
        System.out.println(date);


    }
}
