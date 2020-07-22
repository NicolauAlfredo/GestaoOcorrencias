/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author user
 */
public class DateUtil {

    public static Date strToDate(String data) throws java.text.ParseException {
        if (data == null) {
            return null;
        }
        Date dataF = null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        long timestamp = dateFormat.parse(data).getTime();
        dataF = new Date(timestamp);
        return dataF;
    }

    public static String formataData(Date data) {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.setTime(data);
        return sdf.format(calendar.getTime());
    }
}
