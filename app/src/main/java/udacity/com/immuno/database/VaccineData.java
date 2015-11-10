package udacity.com.immuno.database;

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
    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccine_api_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private String vaccineApiId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "status")
    private int status;

    @Column(name = "schedule_date")
    private Date scheduleDate;

    public VaccineData() {

    }

    protected VaccineData(Parcel in) {
        vaccineName = in.readString();
        vaccineApiId = in.readString();
        userId = in.readLong();
        status = in.readInt();
        long tmpScheduleDate = in.readLong();
        scheduleDate = tmpScheduleDate != -1 ? new Date(tmpScheduleDate) : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vaccineName);
        dest.writeString(vaccineApiId);
        dest.writeLong(userId);
        dest.writeInt(status);
        dest.writeLong(scheduleDate != null ? scheduleDate.getTime() : -1L);
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

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
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
}