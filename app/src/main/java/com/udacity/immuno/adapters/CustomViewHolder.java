package com.udacity.immuno.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.udacity.immuno.R;

/**
 * Created by Alejandro on 11/9/15.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {

    protected TextView textView;

    public CustomViewHolder(View view) {
        super(view);
        this.textView = (TextView) view.findViewById(R.id.title);
    }
}