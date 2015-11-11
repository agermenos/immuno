package com.udacity.immuno.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.udacity.immuno.R;
import com.udacity.immuno.database.VaccineData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alex on 15-11-10.
 */
public class MainActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VaccineData> vaccineDataList;
    private List<Integer> views;
    private Context mContext;

    private List<VaccineData> needsAttentionVaccines;
    private List<VaccineData> upcomingVaccines;
    private List<VaccineData> pastVaccines;

    private static final int TYPE_HEADER_NEEDS_ATTENTION = 0;
    private static final int TYPE_HEADER_UPCOMING = 1;
    private static final int TYPE_HEADER_PAST_VACCINES = 2;
    private static final int TYPE_BUTTON_MORE = 3;
    private static final int TYPE_VACCINE = 4;

    public MainActivityAdapter(Context context, List<VaccineData> vaccineDataList) {
        this.vaccineDataList = vaccineDataList;
        this.mContext = context;

        setViews();
    }

    public void setViews(){

        views = new ArrayList<>();
        views.add(TYPE_HEADER_NEEDS_ATTENTION);

        //TODO: check if there are need attention items
            //if yes: views.add(TYPE_HEADER_NEEDS_ATTENTION);
        //TODO: for every vaccine that needs attention:
            //views.add(TYPE_VACCINE);
        //TODO: rinse repeat

        views.add(TYPE_BUTTON_MORE); //add at the end of the list

    } //setViews

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.header_title) TextView headerTitle;

        public HeaderViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public static class VaccineViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.vaccine_icon) ImageView vaccineIcon;
        @Bind(R.id.vaccine_name) TextView vaccineName;
        @Bind(R.id.vaccine_formal_name) TextView vaccineFormalName;
        @Bind(R.id.vaccine_action)ToggleButton vaccineAction;

        public VaccineViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public static class MoreButtonViewHolder extends RecyclerView.ViewHolder {
        public MoreButtonViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case TYPE_HEADER_NEEDS_ATTENTION:
            case TYPE_HEADER_UPCOMING:
            case TYPE_HEADER_PAST_VACCINES:
                View headerView = mInflater.inflate(R.layout.header_recyclerview, parent, false);
                return new HeaderViewHolder(headerView);

            case TYPE_BUTTON_MORE:
                View buttonMoreView = mInflater.inflate(R.layout.row_more_button, parent, false);
                return new MoreButtonViewHolder(buttonMoreView);

            case TYPE_VACCINE:
                View vaccineView = mInflater.inflate(R.layout.row_vacine_small, parent, false);
                return new VaccineViewHolder(vaccineView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {

            case TYPE_HEADER_NEEDS_ATTENTION:
                final HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.headerTitle.setText("");


                break;

            case TYPE_HEADER_UPCOMING:

                break;

            case TYPE_HEADER_PAST_VACCINES:

                break;

            case TYPE_VACCINE:
                final VaccineViewHolder vaccineView = (VaccineViewHolder) holder;

                //TODO: set data for this vaccine
                //Vaccine vaccine = vaccineDataList.get(position - 1); //account for header view
                vaccineView.vaccineName.setText("");
                vaccineView.vaccineFormalName.setText("");

                //TODO: set icon and set action
                break;

        }
    }

    @Override
    public int getItemCount() {
        return (null != vaccineDataList ? vaccineDataList.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return views.get(position);
    }
}
