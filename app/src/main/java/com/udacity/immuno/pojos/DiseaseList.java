package com.udacity.immuno.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DiseaseList implements Parcelable {

    @SerializedName("diseases_to_vaccine")
    @Expose
    private List<DiseasesToVaccine> diseasesToVaccine = new ArrayList<DiseasesToVaccine>();

    /**
     *
     * @return
     * The diseasesToVaccine
     */
    public List<DiseasesToVaccine> getDiseasesToVaccine() {
        return diseasesToVaccine;
    }

    /**
     *
     * @param diseasesToVaccine
     * The diseases_to_vaccine
     */
    public void setDiseasesToVaccine(List<DiseasesToVaccine> diseasesToVaccine) {
        this.diseasesToVaccine = diseasesToVaccine;
    }


    protected DiseaseList(Parcel in) {
        if (in.readByte() == 0x01) {
            diseasesToVaccine = new ArrayList<DiseasesToVaccine>();
            in.readList(diseasesToVaccine, DiseasesToVaccine.class.getClassLoader());
        } else {
            diseasesToVaccine = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (diseasesToVaccine == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(diseasesToVaccine);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DiseaseList> CREATOR = new Parcelable.Creator<DiseaseList>() {
        @Override
        public DiseaseList createFromParcel(Parcel in) {
            return new DiseaseList(in);
        }

        @Override
        public DiseaseList[] newArray(int size) {
            return new DiseaseList[size];
        }
    };
}