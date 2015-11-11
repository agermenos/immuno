package com.udacity.immuno.utils;

/**
 * Created by Alejandro on 11/10/15.
 */

import com.udacity.immuno.database.VaccineData;
import java.util.Comparator;

public class VaccineCountrySeparator implements Comparator<VaccineData> {

    @Override
    public int compare(VaccineData v1, VaccineData v2) {
        if (v1.getUserId()!=2000 & v2.getUserId()!=2000) {
            return v1.getCasualName().compareTo(v2.getCasualName());
        }
        else return (int)(v1.getUserId()-v2.getUserId());
    }
}
