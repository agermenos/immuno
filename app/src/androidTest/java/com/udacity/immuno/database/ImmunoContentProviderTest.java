package com.udacity.immuno.database;

import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.content.ContentProvider;

/**
 * Created by sengopal on 11/9/15.
 */
public class ImmunoContentProviderTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        ActiveAndroid.initialize(getContext());
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

        /*ContentProvider provider = getProvider();
        Uri uri = ContentProvider.createUri(UserInfo.class, user.getId());
        Cursor cursor = provider.query(uri, null, null, null, null);
        assertNotNull(cursor);*/
        Uri uri = ContentProvider.createUri(UserInfo.class, user.getId());
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        assertNotNull(cursor);
        if (cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndex("user_name"));
                System.out.println(data);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }
}
