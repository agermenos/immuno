package com.udacity.immuno.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.immuno.R;

/**
 * Created by Alejandro on 11/9/15.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {

    protected TextView tvName;
    protected ImageView ibVaccineIcon;
    protected TextView tvFormalName;
    protected ImageView ibVaccineAction;

    public CustomViewHolder(View view) {
        super(view);
        this.tvName = (TextView) view.findViewById(R.id.vaccine_name);
        this.ibVaccineIcon = (ImageView) view.findViewById(R.id.vaccine_icon);
        this.tvFormalName = (TextView) view.findViewById(R.id.vaccine_formal_name);
        this.ibVaccineAction = (ImageView) view.findViewById(R.id.vaccine_action);
    }
}