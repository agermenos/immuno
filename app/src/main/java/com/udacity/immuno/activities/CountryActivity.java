package com.udacity.immuno.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.udacity.immuno.R;
import com.udacity.immuno.adapters.MainActivityAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CountryActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
    public static final String TAG = CountryActivity.class.getSimpleName();

    //private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.8f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.6f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 250;

    private boolean mIsTitleVisible = false;
    private boolean mIsTitleContainerVisible = true;

    private Activity mActivity;

    private LinearLayoutManager mLayoutManager;
    private MainActivityAdapter mAdapter;

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.header_container) RelativeLayout headerContainer;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        mActivity = this;
        ButterKnife.bind(this);

        setUpToolbar();
        setUpHeader();
        setUpRecyclerView();
    }

    public void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //toolbar.setTitle(); //TODO: set title to country name
        toolbar.setTitle("");

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);
    }

    public void setUpHeader(){
        //TODO: add all values to header
        //TODO: add country name, add number of vaccines required
    }

    public void setUpRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS); //for accessibility

        //TODO: feed country data to the adapter
        //mAdapter = new CountryActivityAdapter(mActivity, );
        //mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int appBarOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(appBarOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {

            if(mIsTitleContainerVisible) {
                startAlphaAnimation(headerContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                //mToolbar.setTitle(""); //TODO: set title to country name
                mIsTitleContainerVisible = false;
            }

        } else {

            if (!mIsTitleContainerVisible) {
                startAlphaAnimation(headerContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mToolbar.setTitle("");
                mIsTitleContainerVisible = true;

            }
        }
    }

//    private void handleToolbarTitleVisibility(float percentage) {
//        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
//
//            if(!mIsTitleVisible) {
//                startAlphaAnimation(toolbarTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
//                mIsTitleVisible = true;
//            }
//
//        } else {
//
//            if (mIsTitleVisible) {
//                startAlphaAnimation(toolbarTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
//                mIsTitleVisible = false;
//            }
//        }
//    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

}
