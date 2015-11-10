package com.udacity.immuno.adapters;

/**
 * Created by Alejandro on 11/9/15.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.immuno.R;
import com.udacity.immuno.database.VaccineData;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private List<VaccineData> vaccineDataList;
    private Context mContext;

    public RecycleViewAdapter(Context context, List<VaccineData> vaccineDataList) {
        this.vaccineDataList = vaccineDataList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_vacine_small, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        VaccineData vaccineData = vaccineDataList.get(i);

        customViewHolder.tvName.setText(vaccineData.getCasualName());
        customViewHolder.tvFormalName.setText(vaccineData.getFormalName());
    }

    @Override
    public int getItemCount() {
        return (null != vaccineDataList ? vaccineDataList.size() : 0);
    }
}