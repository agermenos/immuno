package com.udacity.immuno.pojos;

import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sengopal on 11/10/15.
 */
public class PojoHelper {

    public static VaccineData convert(Vaccine v){
        VaccineData vaccine = new VaccineData();

        vaccine.setCasualName(v.getCasualName());
        vaccine.setFormalName(v.getFormalName());
        vaccine.setCategory(v.getCategory());
        vaccine.setDescription(v.getDescription());
        vaccine.setStatus(DBHelper.STATUS_TO_BE_SCHEDULED);
        vaccine.setVaccineApiId(v.getID());
        vaccine.setLink(v.getLink());
        vaccine.setScheduleDate(generateExpirationReminder(v.getExpireMonths()));
        return vaccine;
    }

    private static Date generateExpirationReminder(String expireMonths) {
        if (null != expireMonths && expireMonths.length() > 0) {
            Calendar calendar = Calendar.getInstance();
            //seting the schedule as 1 month from the expiration
            calendar.roll(Calendar.MONTH, Integer.parseInt(expireMonths)-1);
            return calendar.getTime();
        }
        return new Date();
    }
}
