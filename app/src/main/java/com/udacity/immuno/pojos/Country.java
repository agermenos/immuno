package com.udacity.immuno.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Country implements Parcelable {

    @SerializedName("All travelers")
    @Expose
    private List<String> AllTravelers = new ArrayList<String>();
    @SerializedName("Most travelers")
    @Expose
    private List<String> MostTravelers = new ArrayList<String>();
    @SerializedName("Some travelers")
    @Expose
    private List<String> SomeTravelers = new ArrayList<String>();
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("country_url")
    @Expose
    private String countryUrl;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    /**
     *
     * @return
     * The AllTravelers
     */
    public List<String> getAllTravelers() {
        return AllTravelers;
    }

    /**
     *
     * @param AllTravelers
     * The All travelers
     */
    public void setAllTravelers(List<String> AllTravelers) {
        this.AllTravelers = AllTravelers;
    }

    /**
     *
     * @return
     * The MostTravelers
     */
    public List<String> getMostTravelers() {
        return MostTravelers;
    }

    /**
     *
     * @param MostTravelers
     * The Most travelers
     */
    public void setMostTravelers(List<String> MostTravelers) {
        this.MostTravelers = MostTravelers;
    }

    /**
     *
     * @return
     * The SomeTravelers
     */
    public List<String> getSomeTravelers() {
        return SomeTravelers;
    }

    /**
     *
     * @param SomeTravelers
     * The Some travelers
     */
    public void setSomeTravelers(List<String> SomeTravelers) {
        this.SomeTravelers = SomeTravelers;
    }

    /**
     *
     * @return
     * The countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * @param countryName
     * The country_name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     *
     * @return
     * The countryUrl
     */
    public String getCountryUrl() {
        return countryUrl;
    }

    /**
     *
     * @param countryUrl
     * The country_url
     */
    public void setCountryUrl(String countryUrl) {
        this.countryUrl = countryUrl;
    }

    /**
     *
     * @return
     * The imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     *
     * @param imgUrl
     * The img_url
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    protected Country(Parcel in) {
        if (in.readByte() == 0x01) {
            AllTravelers = new ArrayList<String>();
            in.readList(AllTravelers, String.class.getClassLoader());
        } else {
            AllTravelers = null;
        }
        if (in.readByte() == 0x01) {
            MostTravelers = new ArrayList<String>();
            in.readList(MostTravelers, String.class.getClassLoader());
        } else {
            MostTravelers = null;
        }
        if (in.readByte() == 0x01) {
            SomeTravelers = new ArrayList<String>();
            in.readList(SomeTravelers, String.class.getClassLoader());
        } else {
            SomeTravelers = null;
        }
        countryName = in.readString();
        countryUrl = in.readString();
        imgUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (AllTravelers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(AllTravelers);
        }
        if (MostTravelers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(MostTravelers);
        }
        if (SomeTravelers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(SomeTravelers);
        }
        dest.writeString(countryName);
        dest.writeString(countryUrl);
        dest.writeString(imgUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}