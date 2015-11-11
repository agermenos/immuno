package com.udacity.immuno.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.immuno.R;
import com.udacity.immuno.Utility;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;
import com.udacity.immuno.utils.CircleTransform;

public class VaccineInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private VaccineData mVaccineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mVaccineData = getIntent().getParcelableExtra("vaccineInfo");
        long userId = getIntent().getLongExtra("userId", DBHelper.getPrimaryUserId());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        String strCasualName = mVaccineData.getCasualName();
        if(strCasualName == null){
            strCasualName = mVaccineData.getFormalName();
        }
        collapsingToolbar.setTitle(strCasualName);
        TextView vaccineDesc = (TextView) findViewById(R.id.vaccine_description);
        ImageView picture = (ImageView) findViewById(R.id.image);
        vaccineDesc.setText(vaccineData.getDescription());
        Picasso.with(this).load(Utility.randMicrobe()).transform(new CircleTransform()).into(picture);
        vaccineDesc.setText(mVaccineData.getDescription());
        if (mVaccineData.getUserId()!=2000) {
            Picasso.with(this).load(Utility.randMicrobe()).transform(new CircleTransform()).into(picture);
        }
        else {
            Picasso.with(this).load(mVaccineData.getLink()).transform(new CircleTransform()).into(picture);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_add_calendar){
            //add to calendar as a calendar notification
        }else if(v.getId() == R.id.btn_add_reminder){
            //add to calendar using API as just a reminder
        }else if(v.getId() == R.id.btn_save_to_local) {
            //add to local save
        }else if(v.getId() == R.id.btn_appt){
            //call for appt
        }

        
    }
}
