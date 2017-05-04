/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.HiDiary.Visao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 *
 * @author Lyndemberg
 */
public class NewMain {

    
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);
        System.out.println(date);
    }
    
    
    private static boolean validaData(LocalDate data){
        String s = ""+data.getDayOfMonth()+"/"+data.getMonthValue()+"/"+data.getYear()+"";
        DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse (s);
            return true;
        }catch (ParseException ex) {
           return false;
        }
    }
    
    public static boolean isDateValid(String strDate) {
        String dateFormat = "dd/MM/yyyy";
 
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
           return false;
        } 
    }
    
}
