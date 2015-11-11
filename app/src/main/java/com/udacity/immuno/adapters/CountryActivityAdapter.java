package com.udacity.immuno.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.immuno.R;
import com.udacity.immuno.database.VaccineData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alex on 15-11-10.
 */
public class CountryActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VaccineData> vaccineDataList;
    private List<Integer> views;
    private Context mContext;

    private static final int TYPE_DESCRIPTION = 0;
    private static final int TYPE_WARNING = 1;
    private static final int TYPE_VACCINE = 2;
    private static final int TYPE_LINK = 3;

    public CountryActivityAdapter(Context context, List<VaccineData> vaccineDataList) {
        this.vaccineDataList = vaccineDataList;
        this.mContext = context;

        setViews();
    }

    public void setViews(){

        views = new ArrayList<>();
        views.add(TYPE_DESCRIPTION);
        views.add(TYPE_WARNING);

        //TODO: for every vaccine:
        //views.add(TYPE_VACCINE);

        views.add(TYPE_LINK); //add at the end of the list

    } //setViews

    public static class DescriptionViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.country_description) TextView countryDescription;

        public DescriptionViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public static class WarningViewHolder extends RecyclerView.ViewHolder {
        public WarningViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public static class VaccineViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.vaccine_icon) ImageView vaccineIcon;
        @Bind(R.id.vaccine_name) TextView vaccineName;
        @Bind(R.id.vaccine_country_description) TextView vaccineCountryDescription;

        public VaccineViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public static class LinkViewHolder extends RecyclerView.ViewHolder {
        public LinkViewHolder(View v) {
            super(v);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case TYPE_DESCRIPTION:
                View descriptionView = mInflater.inflate(R.layout.row_country_description, parent, false);
                return new DescriptionViewHolder(descriptionView);

            case TYPE_WARNING:
                View warningView = mInflater.inflate(R.layout.row_country_vaccine_large, parent, false);
                return new WarningViewHolder(warningView);

            case TYPE_VACCINE:
                View vaccineView = mInflater.inflate(R.layout.row_country_vaccine_large, parent, false);
                return new VaccineViewHolder(vaccineView);

            case TYPE_LINK:
                View linkView = mInflater.inflate(R.layout.row_button_visit_cdc, parent, false);
                return new VaccineViewHolder(linkView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {

            case TYPE_DESCRIPTION:
                final DescriptionViewHolder descriptionView = (DescriptionViewHolder) holder;

                //TODO: set text
                descriptionView.countryDescription.setText("");

                break;

            case TYPE_WARNING:
                final WarningViewHolder warningView = (WarningViewHolder) holder;

                break;

            case TYPE_VACCINE:
                final VaccineViewHolder vaccineView = (VaccineViewHolder) holder;

                //TODO: set data for this vaccine
                //Vaccine vaccine = vaccineDataList.get(position - 1); //account for header view
                vaccineView.vaccineName.setText("");
                vaccineView.vaccineFormalName.setText("");

                //TODO: set icon and set action
                break;

            case TYPE_LINK:
                final LinkViewHolder linkView = (LinkViewHolder) holder;

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
