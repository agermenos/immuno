package com.udacity.immuno.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.udacity.immuno.pojos.Vaccine;

@Generated("org.jsonschema2pojo")
public class Vaccines implements Parcelable {

    @SerializedName("vaccines")
    @Expose
    private List<Vaccine> vaccines = new ArrayList<Vaccine>();

    /**
     *
     * @return
     * The vaccines
     */
    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    /**
     *
     * @param vaccines
     * The vaccines
     */
    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }


    protected Vaccines(Parcel in) {
        if (in.readByte() == 0x01) {
            vaccines = new ArrayList<Vaccine>();
            in.readList(vaccines, Vaccine.class.getClassLoader());
        } else {
            vaccines = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (vaccines == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(vaccines);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Vaccines> CREATOR = new Parcelable.Creator<Vaccines>() {
        @Override
        public Vaccines createFromParcel(Parcel in) {
            return new Vaccines(in);
        }

        @Override
        public Vaccines[] newArray(int size) {
            return new Vaccines[size];
        }
    };
}