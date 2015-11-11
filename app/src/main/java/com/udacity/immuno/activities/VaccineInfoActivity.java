package com.udacity.immuno.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.immuno.R;
import com.udacity.immuno.Utility;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VaccineInfoActivity extends AppCompatActivity implements View.OnClickListener, AppBarLayout.OnOffsetChangedListener {
    private VaccineData mVaccineData;

    @Bind(R.id.status_tag) ImageView statusTag;
    @Bind(R.id.appbar) AppBarLayout appBarLayout;

    private static final float PERCENTAGE_TO_HIDE_ICON  = 0.8f;
    private static final float PERCENTAGE_TO_SHOW_ICON     = 0.2f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info);

        ButterKnife.bind(this);

        mVaccineData = getIntent().getParcelableExtra("vaccineInfo");
        long userId = getIntent().getLongExtra("userId", DBHelper.getPrimaryUserId());

        setUpToolbar();

        setStatusTag();

        TextView vaccineDesc = (TextView) findViewById(R.id.vaccine_description);
        ImageView headerImage = (ImageView) findViewById(R.id.header_image);
        vaccineDesc.setText(mVaccineData.getDescription());
        Picasso.with(this).load(Utility.randMicrobe()).into(headerImage);
        vaccineDesc.setText(mVaccineData.getDescription());
        if (mVaccineData.getUserId()!=2000) {
            Picasso.with(this).load(Utility.randMicrobe()).into(headerImage);
        }
        else {
            Picasso.with(this).load(mVaccineData.getLink()).into(headerImage);
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
    }

    public void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        String strCasualName = mVaccineData.getCasualName();
        if(strCasualName == null){
            strCasualName = mVaccineData.getFormalName();
        }
        collapsingToolbar.setTitle(strCasualName);
    }

    public void setStatusTag(){

        int status = mVaccineData.getStatus();

        switch (status){

            case DBHelper.STATUS_COMPLETED:
                statusTag.setImageDrawable(getResources().getDrawable(R.drawable.status_tag_received));

                break;
            case DBHelper.STATUS_GOOD_FOR_LIFE:
                statusTag.setImageDrawable(getResources().getDrawable(R.drawable.status_tag_for_life));
                break;
            case DBHelper.STATUS_SCHEDULED:
                statusTag.setImageDrawable(getResources().getDrawable(R.drawable.status_tag_update_soon));
                break;
            case DBHelper.STATUS_TO_BE_SCHEDULED:
                statusTag.setImageDrawable(getResources().getDrawable(R.drawable.status_tag_update_soon));
                break;
            default:
                statusTag.setVisibility(View.GONE);
                break;
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

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        //TODO: fade out icon

    }

}
