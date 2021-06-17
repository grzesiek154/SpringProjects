package com.trainings_notebook.backend;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Instant;


public class TestClass2 {



    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.6");
        BigDecimal b = new BigDecimal("9.2");
        Instant date = Instant.ofEpochSecond(1623574442);

        System.out.println(a.divide(b, MathContext.DECIMAL32));
        System.out.println(a.divide(b, RoundingMode.HALF_UP));
        System.out.println(a.doubleValue() /b.doubleValue());
        System.out.println(date);

    }
}
