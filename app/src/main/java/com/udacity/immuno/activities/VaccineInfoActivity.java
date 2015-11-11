package com.udacity.immuno.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.udacity.immuno.R;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;

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

        //progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        //progressBar.setVisibility(View.VISIBLE);

        /* No Fab button in this Activity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mVaccineData.getCasualName());

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
