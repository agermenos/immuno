package com.udacity.immuno.database;

import android.support.annotation.IntDef;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sengopal on 11/9/15.
 */
public class DBHelper {
    private static long DEFAULT_USER_ID = -1;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

    public static long getPrimaryUserId() {
        if(DEFAULT_USER_ID < 0){
            DEFAULT_USER_ID = getPrimaryUser().getId();
        }
        return DEFAULT_USER_ID;
    }

    public static void addVaccineForUser(VaccineData vaccineData, long userId) {
        vaccineData.setUserId(userId);
        vaccineData.save();
    }

    public static VaccineData checkForTodaysVaccines() {
        List<VaccineData> list = new Select().from(VaccineData.class).execute();
        if(null!=list && !list.isEmpty()){
            Calendar calendar = Calendar.getInstance();
            calendar.roll(Calendar.DAY_OF_MONTH, -1);
            for(VaccineData vaccineData : list){
                if(vaccineData.getScheduleDate().after(calendar.getTime())){
                    return vaccineData;
                }
            }
        }
        return null;
    }

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

    public static VaccineData getVaccineByAPIId(String apiId){
        List<VaccineData> list = new Select().from(VaccineData.class).where("vaccine_api_id = ?", apiId).execute();
        if (list!=null && list.size()!=0)
            return list.get(0);
        else return null;
    }

    public static List<VaccineData> getVaccineForUser(long userId){
       List<VaccineData> list = new Select().from(VaccineData.class).where("user_id = ?", userId).execute();
       return list;
    }

    public static VaccineData addVaccineForUser(String casualName, String formalName, String vaccineApiId, Date scheduledDate, @VaccineStatus int vaccineStatus, long userId, String routine, String description){
        VaccineData vaccineData = new VaccineData();
        vaccineData.setUserId(userId);
        vaccineData.setScheduleDate(scheduledDate);
        vaccineData.setStatus(vaccineStatus);
        vaccineData.setVaccineApiId(vaccineApiId);
        vaccineData.setCasualName(casualName);
        vaccineData.setFormalName(formalName);
        vaccineData.setCategory(routine);
        vaccineData.setDescription(description);
        vaccineData.save();
        return vaccineData;
    }

    public static List<VaccineData> searchVaccinesByName(String vaccineName){
        List<VaccineData> vaccineDataList = new Select().from(VaccineData.class).where("casual_name LIKE ? OR formal_name LIKE ?", new String[]{"%" + vaccineName + "%", "%" + vaccineName + "%"}).execute();
        return vaccineDataList;
    }

    public static UserInfo getPrimaryUser(){
        List<UserInfo> list = new Select().from(UserInfo.class).execute();
        if(null!=list && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public static void setupDemoUserInfo(){
        //Delete existing data
        new Delete().from(UserInfo.class).execute();
        new Delete().from(VaccineData.class).execute();
        //create a new Demo User as this is a brand new install
        UserInfo user = new UserInfo();
        user.setDoctorName("Dr. Erik");
        user.setHealthProvider("Google Healthcare System");
        user.setIsPrimary(true);
        user.setRelationship("self");
        user.setUserName("Alejandros");
        user.save();
        Long userId = user.getId();
        //Vaccines Senthi July 7th 1984
        //ADD EXTRA ARGUMENTS FOR ROUTINE AND CHANGE STATUS
        addVaccineForUser("DTaP", "None", "2", getDate("10-Nov-2009"),STATUS_COMPLETED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        addVaccineForUser("Hepatitis A","None", "3", getDate("2-Jan-2000"),STATUS_COMPLETED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        addVaccineForUser("Hepatitis B", "None", "4",getDate("2-Jan-2000"),STATUS_GOOD_FOR_LIFE,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        addVaccineForUser("Haemophilus Influenzae type b", "None", "5",getDate("2-Aug-2011"),STATUS_COMPLETED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        //addVaccineForUser("HPV - Cervarix", "None", "6",getDate("10-Nov-2014"),STATUS_COMPLETED,userId, "Routine");
        addVaccineForUser("HPV - Gardasil-9", "None", "7",getDate("20-Dec-2012"),STATUS_COMPLETED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        //addVaccineForUser("HPV - Gardasil", "None", "8",getDate("10-Nov-2014"),STATUS_COMPLETED,userId, "Routine");
        //addVaccineForUser("Influenza - Live, Intranasal", "None", "9",getDate("10-Nov-2014"),STATUS_COMPLETED,userId, "Routine");
        addVaccineForUser("Influenza - Inactivated", "None", "10",getDate("10-Nov-2014"),STATUS_SCHEDULED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        //addVaccineForUser("MMR","11",getDate("10-Nov-2014"),STATUS_COMPLETED,userId, "Routine");
        addVaccineForUser("MMRV","Measles/Mumps/Rubella", "12",getDate("13-Feb-2008"),STATUS_COMPLETED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        //addVaccineForUser("Meningococcal","None","13",getDate("10-Nov-2013"),STATUS_COMPLETED,userId, "Routine");
        addVaccineForUser("MenB", "Serogroup B Meningococcal ", "14",getDate("10-Dec-2014"),STATUS_COMPLETED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        addVaccineForUser("PCV13","Pneumococcal Conjugate", "15",getDate("10-Dec-2014"),STATUS_TO_BE_SCHEDULED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        addVaccineForUser("PPSV23","Pneumococcal Polysaccharide", "16",getDate("10-Dec-2014"),STATUS_COMPLETED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        addVaccineForUser("Polio", "None", "17",getDate("3-Sep-1990"),STATUS_COMPLETED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        addVaccineForUser("Rotavirus","None", "18",getDate("7-Jan-1985"),STATUS_GOOD_FOR_LIFE,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
        //addVaccineForUser("Herpes Zoster","Shingles", "19",getDate("10-Nov-2014"),STATUS_COMPLETED,userId, "Routine"); //Only for people > 60 years old
        //addVaccineForUser("Tetanus, Diphtheria, Pertussis", "Tdap", "20",getDate("10-Nov-2014"),STATUS_COMPLETED,userId, "Routine");
        //addVaccineForUser("Tetanus, Diphtheria","Td", "21",getDate("10-Nov-2014"),STATUS_COMPLETED,userId, "Routine");
        addVaccineForUser("Chickenpox","Varicella", "22",getDate("10-Nov-2011"),STATUS_COMPLETED,userId, "Routine", "Routine vaccines are those that are recommended for everyone in the United States, depending on age and your vaccine history. (See routine vaccine schedules.) Most people think of these as childhood vaccines that you get before starting school, but some vaccines are routinely recommended for adults, and some are recommended every year (a flu vaccine) or every 10 years (a tetanus booster).");
    }

    private static Date getDate(String s) {
        try {
            return DATE_FORMAT.parse(s);
        } catch (ParseException e) {
            return new Date();
        }
    }

}