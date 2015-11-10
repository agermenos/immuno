package com.udacity.immuno.database;

import android.support.annotation.IntDef;

import com.activeandroid.query.Select;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.List;

/**
 * Created by sengopal on 11/9/15.
 */
public class DBHelper {
    @IntDef({STATUS_GOOD_FOR_LIFE, STATUS_COMPLETED, STATUS_SCHEDULED, STATUS_TO_BE_SCHEDULED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VaccineStatus {}

    public static final int STATUS_GOOD_FOR_LIFE = 1;
    public static final int STATUS_COMPLETED = 2;
    public static final int STATUS_SCHEDULED = 3;
    public static final int STATUS_TO_BE_SCHEDULED = 4;

    public static UserInfo getUser(long id){
        List<UserInfo> list = new Select().from(UserInfo.class).where("_Id = ?", id).execute();
        if(null!=list && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    public static VaccineData getVaccine(long id){
        List<VaccineData> list = new Select().from(VaccineData.class).where("_Id = ?", id).execute();
        if(null!=list && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    public static VaccineData addVaccineForUser(String vaccineName, String vaccineApiId, Date scheduledDate, @VaccineStatus int vaccineStatus, long userId){
        VaccineData vaccineData = new VaccineData();
        vaccineData.setUserId(userId);
        vaccineData.setScheduleDate(scheduledDate);
        vaccineData.setStatus(vaccineStatus);
        vaccineData.setVaccineApiId(vaccineApiId);
        vaccineData.setVaccineName(vaccineName);
        vaccineData.save();
        return vaccineData;
    }

    public static List<VaccineData> searchVaccinesByName(String vaccineName){
        List<VaccineData> vaccineDataList = new Select().from(VaccineData.class).where("vaccine_name LIKE ?", new String[]{"%" + vaccineName + "%"}).execute();
        return vaccineDataList;
    }
}