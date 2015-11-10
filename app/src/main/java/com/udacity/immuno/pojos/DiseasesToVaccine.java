package com.udacity.immuno.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DiseasesToVaccine implements Parcelable {

    @SerializedName("disease_name")
    @Expose
    private String diseaseName;
    @SerializedName("vaccine_names")
    @Expose
    private List<String> vaccineNames = new ArrayList<String>();

    /**
     *
     * @return
     * The diseaseName
     */
    public String getDiseaseName() {
        return diseaseName;
    }

    /**
     *
     * @param diseaseName
     * The disease_name
     */
    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    /**
     *
     * @return
     * The vaccineNames
     */
    public List<String> getVaccineNames() {
        return vaccineNames;
    }

    /**
     *
     * @param vaccineNames
     * The vaccine_names
     */
    public void setVaccineNames(List<String> vaccineNames) {
        this.vaccineNames = vaccineNames;
    }


    protected DiseasesToVaccine(Parcel in) {
        diseaseName = in.readString();
        if (in.readByte() == 0x01) {
            vaccineNames = new ArrayList<String>();
            in.readList(vaccineNames, String.class.getClassLoader());
        } else {
            vaccineNames = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diseaseName);
        if (vaccineNames == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(vaccineNames);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DiseasesToVaccine> CREATOR = new Parcelable.Creator<DiseasesToVaccine>() {
        @Override
        public DiseasesToVaccine createFromParcel(Parcel in) {
            return new DiseasesToVaccine(in);
        }

        @Override
        public DiseasesToVaccine[] newArray(int size) {
            return new DiseasesToVaccine[size];
        }
    };
}