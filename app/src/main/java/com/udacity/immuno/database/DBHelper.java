package com.udacity.immuno.database;

import android.support.annotation.IntDef;

import com.activeandroid.query.Select;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sengopal on 11/9/15.
 */
public class DBHelper {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
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

    public static UserInfo getPrimaryUser(){
        List<UserInfo> list = new Select().from(UserInfo.class).where("is_primary = ?", Boolean.TRUE).execute();
        if(null!=list && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public static void setupDemoUserInfo(){
        //create a new Demo User as this is a brand new install
        UserInfo user = new UserInfo();
        user.setDoctorName("Dr. Erik");
        user.setHealthProvider("Google Healthcare System");
        user.setIsPrimary(true);
        user.setRelationship("self");
        user.setUserName("Alejandros");
        user.save();
        Long userId = user.getId();
        //Vaccines
        addVaccineForUser("DTaP","2",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Hepatitis A","3",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Hepatitis B","4",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Haemophilus Influenzae type b","5",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("HPV - Cervarix","6",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("HPV - Gardasil-9","7",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("HPV - Gardasil","8",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Influenza - Live, Intranasal","9",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Influenza - Inactivated","10",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("MMR","11",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("MMRV","12",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Meningococcal","13",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("MenB","14",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("PCV13","15",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("PPSV23","16",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Polio","17",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Rotavirus","18",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Herpes Zoster","19",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Tetanus, Diphtheria, Pertussis","20",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Tetanus, Diphtheria","21",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
        addVaccineForUser("Chickenpox","22",getDate("10-Nov-2014"),STATUS_COMPLETED,userId);
    }

    private static Date getDate(String s) {
        try {
            return DATE_FORMAT.parse(s);
        } catch (ParseException e) {
            return new Date();
        }
    }

}