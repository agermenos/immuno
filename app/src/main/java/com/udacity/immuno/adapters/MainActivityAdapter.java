package com.udacity.immuno.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.immuno.R;
import com.udacity.immuno.activities.CountryActivity;
import com.udacity.immuno.activities.VaccineInfoActivity;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    VaccineData vaccineData;

    private List<VaccineData> needsAttentionVaccines;
    private List<VaccineData> upcomingVaccines;
    private List<VaccineData> pastVaccines;

    private static final int TYPE_HEADER_NEEDS_ATTENTION = 0;
    private static final int TYPE_HEADER_UPCOMING = 1;
    private static final int TYPE_HEADER_PAST_VACCINES = 2;
    private static final int TYPE_BUTTON_MORE = 3;
    private static final int TYPE_VACCINE_ATTENTION = 4;
    private static final int TYPE_VACCINE_UPCOMING = 5;
    private static final int TYPE_VACCINE_PAST = 6;

    public MainActivityAdapter(Context context, List<VaccineData> vaccineDataList) {
        this.vaccineDataList = vaccineDataList;
        loadList(vaccineDataList);
        this.mContext = context;
        setViews();
    }

    private void loadList(List<VaccineData> vaccineDataList) {
        needsAttentionVaccines = new ArrayList<>();
        upcomingVaccines = new ArrayList<>();
        pastVaccines = new ArrayList<>();
        for (VaccineData vd:vaccineDataList){
            switch (vd.getStatus()) {
                case DBHelper.STATUS_TO_BE_SCHEDULED:
                    needsAttentionVaccines.add(vd);
                    break;
                case DBHelper.STATUS_SCHEDULED:
                    upcomingVaccines.add(vd);
                    break;
                default:
                    pastVaccines.add(vd);
                    break;
            }
        }
    }

    public void setViews(){

        views = new ArrayList<>();


        if(needsAttentionVaccines != null && needsAttentionVaccines.size() > 0){
            views.add(TYPE_HEADER_NEEDS_ATTENTION);

            int size = needsAttentionVaccines.size();

            for(int i = 0; i < size; i++){
                views.add(TYPE_VACCINE_ATTENTION);
            }
        }

        if(upcomingVaccines != null && upcomingVaccines.size() > 0){
            views.add(TYPE_HEADER_UPCOMING);

            int size = upcomingVaccines.size();

            for(int i = 0; i < size; i++){
                views.add(TYPE_VACCINE_UPCOMING);
            }
        }

        if(pastVaccines != null && pastVaccines.size() > 0){
            views.add(TYPE_HEADER_PAST_VACCINES);

            int size = pastVaccines.size();

            for(int i = 0; i < size; i++){
                views.add(TYPE_VACCINE_PAST);
            }
        }

        views.add(TYPE_BUTTON_MORE); //add at the end of the list

    } //setViews

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.header_title) TextView headerTitle;

        public HeaderViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public class VaccineViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.vaccine_icon) ImageView vaccineIcon;
        @Bind(R.id.vaccine_name) TextView vaccineName;
        @Bind(R.id.vaccine_formal_name) TextView vaccineStatusLabel;

        public VaccineViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vaccineData = new VaccineData();
                    vaccineData.setCasualName(vaccineName.getText().toString());
                    vaccineData.setFormalName(vaccineName.getText().toString());
                    vaccineData.setDescription("A vaccine is a biological preparation that provides active acquired immunity to a particular disease. A vaccine typically contains an agent that resembles a disease-causing micro-organism and is often made from weakened or killed forms of the microbe, its toxins or one of its surface proteins.");
                    vaccineData.setStatus(DBHelper.STATUS_COMPLETED);
                    vaccineData.setScheduleDate(new Date());
                    Intent intent;
                    intent = new Intent(mContext, VaccineInfoActivity.class);
                    intent.putExtra("vaccineInfo", vaccineData);
                    intent.putExtra("userId", DBHelper.getPrimaryUserId());
                    mContext.startActivity(intent);
                }
            });
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

            case TYPE_VACCINE_ATTENTION:
                View vaccineAttentionView = mInflater.inflate(R.layout.row_vaccine_main, parent, false);
                return new VaccineViewHolder(vaccineAttentionView);

            case TYPE_VACCINE_UPCOMING:
                View vaccineUpcomingView = mInflater.inflate(R.layout.row_vaccine_main, parent, false);
                return new VaccineViewHolder(vaccineUpcomingView);

            case TYPE_VACCINE_PAST:
                View vaccinePastView = mInflater.inflate(R.layout.row_vaccine_main, parent, false);
                return new VaccineViewHolder(vaccinePastView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {

            case TYPE_HEADER_NEEDS_ATTENTION:
                final HeaderViewHolder needsAttentionView = (HeaderViewHolder) holder;
                needsAttentionView.headerTitle.setText("Needs Attention");
                break;

            case TYPE_HEADER_UPCOMING:
                final HeaderViewHolder upcomingView = (HeaderViewHolder) holder;
                upcomingView.headerTitle.setText("Upcoming");
                break;

            case TYPE_HEADER_PAST_VACCINES:
                final HeaderViewHolder pastVaccinesView = (HeaderViewHolder) holder;
                pastVaccinesView.headerTitle.setText("Past Vaccines");
                break;

            case TYPE_VACCINE_ATTENTION:
                final VaccineViewHolder vaccineAttentionView = (VaccineViewHolder) holder;

                //TODO: set data for this vaccine
                VaccineData vaccineAttention = needsAttentionVaccines.get(position - 1); //account for header view
                vaccineAttentionView.vaccineName.setText(vaccineAttention.getCasualName());

                Date date = vaccineAttention.getScheduleDate();
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                String dateString = sdf.format(date);

                vaccineAttentionView.vaccineStatusLabel.setText("Needs Update");

                break;

            case TYPE_VACCINE_UPCOMING:
                final VaccineViewHolder vaccineUpcomingView = (VaccineViewHolder) holder;

                position = position - needsAttentionVaccines.size();

                //TODO: set data for this vaccine
                VaccineData vaccineUpcoming = upcomingVaccines.get(position - 2); //account for header view
                vaccineUpcomingView.vaccineName.setText(vaccineUpcoming.getCasualName());

                Date upcomingDate = vaccineUpcoming.getScheduleDate();
                SimpleDateFormat upcomingSdf = new SimpleDateFormat("MMM dd, yyyy");
                String upcomingDateString = upcomingSdf.format(upcomingDate);

                vaccineUpcomingView.vaccineStatusLabel.setText("Schedule for " + upcomingDateString);

                break;

            case TYPE_VACCINE_PAST:
                final VaccineViewHolder vaccinePastView = (VaccineViewHolder) holder;

                position = position - (needsAttentionVaccines.size() + upcomingVaccines.size());

                //TODO: set data for this vaccine
                VaccineData vaccinePast = pastVaccines.get(position - 3); //account for header view
                vaccinePastView.vaccineName.setText(vaccinePast.getCasualName());

                Date pastDate = vaccinePast.getScheduleDate();
                SimpleDateFormat pastSdf = new SimpleDateFormat("MMM dd, yyyy");
                String pastDateString = pastSdf.format(pastDate);
                vaccinePastView.vaccineStatusLabel.setText("Recevied " + pastDateString);

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
