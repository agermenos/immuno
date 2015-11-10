package udacity.com.immuno;

import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;

import com.activeandroid.content.ContentProvider;

import com.udacity.immuno.database.UserInfo;

/**
 * Created by sengopal on 11/9/15.
 */
public class ImmunoContentProviderTest extends ProviderTestCase2<ContentProvider> {


    public ImmunoContentProviderTest() {
        super(ContentProvider.class, ContentProvider.class.getName());
    }

    @Override
    protected void setUp() throws Exception {
        //ActiveAndroid.initialize(this);
    }

    public void testContentProvider() {
        UserInfo user = new UserInfo();
        user.setDoctorName("doctor-name");
        user.setHealthProvider("health-provider");
        user.setIsPrimary(true);
        user.setRelationship("self");
        user.setUserName("user-name");
        user.save();
        assertTrue(user.getId() > 0);

        ContentProvider provider = getProvider();
        Uri uri = ContentProvider.createUri(UserInfo.class, user.getId());
        Cursor cursor = provider.query(uri, null, null, null, null);
        assertNotNull(cursor);
    }
}
