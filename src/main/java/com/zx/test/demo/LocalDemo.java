package com.zx.test.demo;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * created by zengqintao on 2018-06-11 14:41 .
 **/
public class LocalDemo {
    public static void main(String[] args) {
        String date = LocalDate.now().toString();
        int Hour = LocalTime.now().getHour();
        String lasttime = "lasttime:[dateTprehour:00:00Z TO dateTnexthour:00:00Z]";
        lasttime = lasttime.replaceAll("date", date).replaceAll("prehour", String.valueOf(Hour - 1)).replaceAll("nexthour", String.valueOf(Hour));
        System.out.println(lasttime);
        String bu = "lasttime:[{0}T{1}:00:00Z TO {0}T{2}:00:00Z]";
        String names = MessageFormat.format(bu, date, Hour - 1, Hour);
        System.out.println(names);
        System.out.println(date + "***" + Hour);
    }
}
