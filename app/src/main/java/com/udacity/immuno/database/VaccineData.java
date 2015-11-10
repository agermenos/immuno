package com.udacity.immuno.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by sengopal on 11/9/15.
 */
@Table(name = "VaccineData", id = BaseColumns._ID)
public class VaccineData extends Model implements Parcelable {
    @Column(name = "formalName")
    private String formalName;

    @Column(name = "casualName")
    private String casualName;

    @Column(name = "vaccine_api_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private String vaccineApiId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "status")
    private int status;

    @Column(name = "schedule_date")
    private Date scheduleDate;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    public VaccineData() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFormalName() {
        return formalName;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    public String getCasualName() {
        return casualName;
    }

    public void setCasualName(String casualName) {
        this.casualName = casualName;
    }

    public String getVaccineApiId() {
        return vaccineApiId;
    }

    public void setVaccineApiId(String vaccineApiId) {
        this.vaccineApiId = vaccineApiId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    protected VaccineData(Parcel in) {
        formalName = in.readString();
        casualName = in.readString();
        vaccineApiId = in.readString();
        userId = in.readLong();
        status = in.readInt();
        long tmpScheduleDate = in.readLong();
        scheduleDate = tmpScheduleDate != -1 ? new Date(tmpScheduleDate) : null;
        category = in.readString();
        description = in.readString();
        link = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(formalName);
        dest.writeString(casualName);
        dest.writeString(vaccineApiId);
        dest.writeLong(userId);
        dest.writeInt(status);
        dest.writeLong(scheduleDate != null ? scheduleDate.getTime() : -1L);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeString(link);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VaccineData> CREATOR = new Parcelable.Creator<VaccineData>() {
        @Override
        public VaccineData createFromParcel(Parcel in) {
            return new VaccineData(in);
        }

        @Override
        public VaccineData[] newArray(int size) {
            return new VaccineData[size];
        }
    };
}