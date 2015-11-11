package com.udacity.immuno.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CountryList implements Parcelable {

    @SerializedName("countries")
    @Expose
    private List<Country> countries = new ArrayList<Country>();

    /**
     *
     * @return
     * The countries
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     *
     * @param countries
     * The countries
     */
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public CountryList(){
    }

    public CountryList(Parcel in) {
        if (in.readByte() == 0x01) {
            countries = new ArrayList<Country>();
            in.readList(countries, Country.class.getClassLoader());
        } else {
            countries = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (countries == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(countries);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CountryList> CREATOR = new Parcelable.Creator<CountryList>() {
        @Override
        public CountryList createFromParcel(Parcel in) {
            return new CountryList(in);
        }

        @Override
        public CountryList[] newArray(int size) {
            return new CountryList[size];
        }
    };
}