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
import com.udacity.immuno.Utility;
import com.udacity.immuno.database.DBHelper;
import com.udacity.immuno.database.VaccineData;

import java.util.Collections;
import java.util.List;
import com.udacity.immuno.utils.VaccineCountrySeparator;

public class RecycleViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private List<VaccineData> vaccineDataList;
    private Context mContext;
    private CustomViewHolder.CustomViewItemClickListener customItemClickListener;

    public RecycleViewAdapter(Context context, List<VaccineData> vaccineDataList, CustomViewHolder.CustomViewItemClickListener customItemClickListener) {
        Collections.sort(vaccineDataList, new VaccineCountrySeparator());
        this.vaccineDataList = vaccineDataList;
        this.mContext = context;
        this.customItemClickListener = customItemClickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_vaccine_search_results, null);

        final CustomViewHolder viewHolder = new CustomViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemClickListener.onItemClicked(
                        vaccineDataList.get(viewHolder.getLayoutPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        VaccineData vaccineData = vaccineDataList.get(i);
        if (vaccineData.getUserId()<2000) {
            customViewHolder.ibVaccineIcon.setImageResource(R.drawable.ic_vaccine_regular);
            VaccineData myVaccine = DBHelper.getVaccineByAPIId(vaccineData.getVaccineApiId());
            if (myVaccine==null) {
                customViewHolder.ibVaccineAction.setImageResource(R.drawable.ic_add_circle_outline_24dp);
            }
            else {
                switch (vaccineData.getStatus()) {
                    case DBHelper.STATUS_COMPLETED:
                        customViewHolder.ibVaccineAction.setColorFilter(R.drawable.ic_protected_green_24dp);
                        break;
                    case DBHelper.STATUS_GOOD_FOR_LIFE:
                        customViewHolder.ibVaccineAction.setImageResource(R.drawable.ic_protected_grey_24dp);
                        break;
                    case DBHelper.STATUS_SCHEDULED:
                        customViewHolder.ibVaccineAction.setImageResource(R.drawable.ic_protected_yellow_24dp);
                        break;
                    case DBHelper.STATUS_TO_BE_SCHEDULED:
                        customViewHolder.ibVaccineAction.setImageResource(R.drawable.ic_protected_yellow_24dp);
                        break;
                    default:
                        customViewHolder.ibVaccineAction.setImageResource(R.drawable.ic_protected_red_24dp);
                }
            }
        }
        else {
            customViewHolder.ibVaccineIcon.setImageResource(R.drawable.ic_country_icon);
            customViewHolder.ibVaccineAction.setVisibility(View.INVISIBLE);
        }
        customViewHolder.tvName.setText(!vaccineData.getCasualName().equals("None") ?
                Utility.capitalize(vaccineData.getCasualName()) :
                Utility.capitalize(vaccineData.getFormalName()));
        customViewHolder.tvFormalName.setText(vaccineData.getFormalName());
    }

    @Override
    public int getItemCount() {
        return (null != vaccineDataList ? vaccineDataList.size() : 0);
    }
}