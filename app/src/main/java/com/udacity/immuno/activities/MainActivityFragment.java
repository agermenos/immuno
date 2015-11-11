package com.udacity.immuno.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.immuno.R;
import com.udacity.immuno.adapters.MainActivityAdapter;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.UserInfo;
import com.udacity.immuno.database.VaccineData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public static final String TAG = MainActivityFragment.class.getSimpleName();

    private Context mContext;

    private LinearLayoutManager mLayoutManager;
    private MainActivityAdapter mAdapter;

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mContext = getActivity();
        ButterKnife.bind(this, rootView);

        setUpRecyclerView();

        return rootView;

    }

    public void setUpRecyclerView(){

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        UserInfo ui = DBHelper.getPrimaryUser();
        List<VaccineData> vaccineDataList = DBHelper.getVaccineForUser(ui.getId());

        //TODO: create adapter
        mAdapter  = new MainActivityAdapter(getActivity(), vaccineDataList);
//        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS); //for accessibility
    }
}
