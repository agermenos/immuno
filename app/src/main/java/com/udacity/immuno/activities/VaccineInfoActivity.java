package com.udacity.immuno.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.udacity.immuno.R;
import com.udacity.immuno.Utility;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;
import com.udacity.immuno.utils.CircleTransform;

import java.util.Date;

public class VaccineInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_TAG = VaccineInfoActivity.class.getSimpleName();
    private VaccineData mVaccineData;
    private long mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mVaccineData = getIntent().getParcelableExtra("vaccineInfo");
        mUserId = getIntent().getLongExtra("userId", DBHelper.getPrimaryUserId());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        String strCasualName = mVaccineData.getCasualName();
        if (strCasualName == null) {
            strCasualName = mVaccineData.getFormalName();
        }
        collapsingToolbar.setTitle(strCasualName);
        TextView vaccineDesc = (TextView) findViewById(R.id.vaccine_description);
        ImageView picture = (ImageView) findViewById(R.id.image);
        vaccineDesc.setText(mVaccineData.getDescription());
        Picasso.with(this).load(Utility.randMicrobe()).transform(new CircleTransform()).into(picture);
        vaccineDesc.setText(mVaccineData.getDescription());
        if (mVaccineData.getUserId() != 2000) {
            Picasso.with(this).load(Utility.randMicrobe()).transform(new CircleTransform()).into(picture);
        } else {
            Picasso.with(this).load(mVaccineData.getLink()).transform(new CircleTransform()).into(picture);
        }


        RelativeLayout moreInfoLayout = (RelativeLayout) findViewById(R.id.more_info_button);
        final String vaccine_info_url = mVaccineData.getLink();
        moreInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(vaccine_info_url));
                startActivity(browserIntent);
            }
        });

        //fab button listeners
        FloatingActionButton fabBtn1 = (FloatingActionButton) findViewById(R.id.btn_add_calendar);
        fabBtn1.setOnClickListener(this);
        FloatingActionButton fabBtn2 = (FloatingActionButton) findViewById(R.id.btn_appt);
        fabBtn2.setOnClickListener(this);
        FloatingActionButton fabBtn3 = (FloatingActionButton) findViewById(R.id.btn_add_reminder);
        fabBtn3.setOnClickListener(this);
        FloatingActionButton fabBtn4 = (FloatingActionButton) findViewById(R.id.btn_save_to_local);
        fabBtn4.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void onClick(View v) {
        Log.d(LOG_TAG, "View clicked: " + v.getId());
        if (v.getId() == R.id.btn_add_calendar) {
            //add to calendar as a calendar notification
            mVaccineData.setScheduleDate(new Date());
            DBHelper.addVaccineForUser(mVaccineData, mUserId);
            Snackbar.make(v, "Vaccine added to Calendar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (v.getId() == R.id.btn_add_reminder) {
            //add to calendar using API as just a reminder
            Snackbar.make(v, "Calendar reminder added", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (v.getId() == R.id.btn_save_to_local) {
            //add to local save
            DBHelper.addVaccineForUser(mVaccineData, mUserId);
            Snackbar.make(v, "Vaccine added to list", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else if (v.getId() == R.id.btn_appt) {
            //call for appt
            Intent i = new Intent();
            i.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.DialtactsContactsEntryActivity"));
            i.setAction("android.intent.action.MAIN");
            i.addCategory("android.intent.category.LAUNCHER");
            i.addCategory("android.intent.category.DEFAULT");
            startActivity(i);
        }
    }
}
