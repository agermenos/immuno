package udacity.com.immuno;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.UserInfo;
import com.udacity.immuno.database.VaccineData;

import java.util.Date;

/**
 * Created by sengopal on 11/9/15.
 */
public class DBTest extends ApplicationTestCase<Application> {
    public DBTest() {
        super(Application.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
    }

    protected void tearDown() throws Exception {
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
        VaccineData vaccineData = DBHelper.addVaccineForUser("vaccine-name", "1234343", new Date(), DBHelper.STATUS_GOOD_FOR_LIFE, user.getId());
        assertTrue(vaccineData.getId() > 0);
    }
}
