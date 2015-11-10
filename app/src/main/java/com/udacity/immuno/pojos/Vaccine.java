package com.udacity.immuno.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Vaccine implements Parcelable {

    @SerializedName("_ID")
    @Expose
    private String ID;
    @SerializedName("casual_name")
    @Expose
    private String casualName;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("formal_name")
    @Expose
    private String formalName;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("expire_months")
    @Expose
    private String expireMonths;

    /**
     *
     * @return
     * The ID
     */
    public String getID() {
        return ID;
    }

    /**
     *
     * @param ID
     * The _ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     * The casualName
     */
    public String getCasualName() {
        return casualName;
    }

    /**
     *
     * @param casualName
     * The casual_name
     */
    public void setCasualName(String casualName) {
        this.casualName = casualName;
    }

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The formalName
     */
    public String getFormalName() {
        return formalName;
    }

    /**
     *
     * @param formalName
     * The formal_name
     */
    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The expireMonths
     */
    public String getExpireMonths() {
        return expireMonths;
    }

    /**
     *
     * @param expireMonths
     * The expire_months
     */
    public void setExpireMonths(String expireMonths) {
        this.expireMonths = expireMonths;
    }


    protected Vaccine(Parcel in) {
        ID = in.readString();
        casualName = in.readString();
        category = in.readString();
        description = in.readString();
        formalName = in.readString();
        link = in.readString();
        expireMonths = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(casualName);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeString(formalName);
        dest.writeString(link);
        dest.writeString(expireMonths);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Vaccine> CREATOR = new Parcelable.Creator<Vaccine>() {
        @Override
        public Vaccine createFromParcel(Parcel in) {
            return new Vaccine(in);
        }

        @Override
        public Vaccine[] newArray(int size) {
            return new Vaccine[size];
        }
    };
}