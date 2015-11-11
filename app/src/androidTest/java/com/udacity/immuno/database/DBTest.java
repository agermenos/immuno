package com.udacity.immuno.database;

import android.test.AndroidTestCase;

import com.activeandroid.query.Delete;

import java.util.Date;
import java.util.List;

/**
 * Created by sengopal on 11/9/15.
 */
public class DBTest extends AndroidTestCase {

    public void setUp() throws Exception {
        super.setUp();
        new Delete().from(VaccineData.class).execute();
        new Delete().from(UserInfo.class).execute();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCRUDForUserInfo(){
        UserInfo user = new UserInfo();
        user.setDoctorName("doctor-name");
        user.setHealthProvider("health-provider");
        user.setIsPrimary(true);
        user.setRelationship("self");
        user.setUserName("user-name");
        user.save();
        assertTrue(user.getId() > 0);
        UserInfo userFetched = DBHelper.getUser(user.getId());
        assertTrue(userFetched.getRelationship().equals("self"));
        userFetched.delete();
    }

    public void testCRUDForVaccineData(){
        UserInfo user = new UserInfo();
        user.setDoctorName("doctor-name");
        user.setHealthProvider("health-provider");
        user.setIsPrimary(true);
        user.setRelationship("self");
        user.setUserName("user-name");
        user.save();
        assertTrue(user.getId() > 0);
        VaccineData vaccineData = DBHelper.addVaccineForUser("casual-name","formal-name", "1234343", new Date(), DBHelper.STATUS_GOOD_FOR_LIFE, user.getId(),"routine");
        assertTrue(vaccineData.getId() > 0);
        vaccineData = DBHelper.getVaccine(vaccineData.getId());
        assertTrue(vaccineData.getId() > 0);
        assertTrue(vaccineData.getCasualName().equals("casual-name"));
    }

    public void testSearchVaccines(){
        UserInfo user = new UserInfo();
        user.setDoctorName("doctor-name");
        user.setHealthProvider("health-provider");
        user.setIsPrimary(true);
        user.setRelationship("self");
        user.setUserName("user-name");
        user.save();
        assertTrue(user.getId() > 0);
        VaccineData vaccineData = DBHelper.addVaccineForUser("casual-name","formal-name", "1234343", new Date(), DBHelper.STATUS_GOOD_FOR_LIFE, user.getId(), "routine");
        assertTrue(vaccineData.getId() > 0);
        List<VaccineData> vaccineDataList = DBHelper.searchVaccinesByName("casual-name");
        assertTrue(vaccineDataList.size()==1);
    }

    public void testSearchVaccinesForToday(){
        UserInfo user = new UserInfo();
        user.setDoctorName("doctor-name");
        user.setHealthProvider("health-provider");
        user.setIsPrimary(true);
        user.setRelationship("self");
        user.setUserName("user-name");
        user.save();
        assertTrue(user.getId() > 0);
        VaccineData vaccineData = DBHelper.addVaccineForUser("casual-name", "formal-name", "1234343", new Date(), DBHelper.STATUS_GOOD_FOR_LIFE, user.getId(), "routine");
        assertTrue(vaccineData.getId() > 0);
        VaccineData vaccineData1 = DBHelper.checkForTodaysVaccines();
        assertTrue(null!=vaccineData1);
    }
}
