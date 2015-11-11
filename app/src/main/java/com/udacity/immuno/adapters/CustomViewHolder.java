package com.udacity.immuno.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.immuno.R;
import com.udacity.immuno.database.VaccineData;

/**
 * Created by Alejandro on 11/9/15.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected VaccineData vaccineData;
    protected TextView tvName;
    protected ImageView ibVaccineIcon;
    protected TextView tvFormalName;
    protected ImageView ibVaccineAction;
    private CustomViewItemClickListener itemClickListener;

    public CustomViewHolder(View view) {
        super(view);
        this.tvName = (TextView) view.findViewById(R.id.vaccine_name);
        this.ibVaccineIcon = (ImageView) view.findViewById(R.id.vaccine_icon);
        this.tvFormalName = (TextView) view.findViewById(R.id.vaccine_formal_name);
        this.ibVaccineAction = (ImageView) view.findViewById(R.id.vaccine_action);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClicked(vaccineData);
    }

    public static interface CustomViewItemClickListener {
        public void onItemClicked(VaccineData vaccineData);
    }
}