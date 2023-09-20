package com.miasol.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class MyTimeUtils {

    public static LocalDate getLocalDate(){
        LocalDate l = LocalDate.now();
        return l.plusYears(1);
    }


}
