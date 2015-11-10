package udacity.com.immuno.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by sengopal on 11/9/15.
 */
@Table(name = "UserInfo", id = BaseColumns._ID)
public class UserInfo extends Model implements Parcelable {
    @Column(name = "relationship")
    private String relationship;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "health_provider")
    private String healthProvider;

    @Column(name = "is_primary")
    private boolean isPrimary;

    public UserInfo(){

    }
    protected UserInfo(Parcel in) {
        relationship = in.readString();
        userName = in.readString();
        doctorName = in.readString();
        healthProvider = in.readString();
        isPrimary = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(relationship);
        dest.writeString(userName);
        dest.writeString(doctorName);
        dest.writeString(healthProvider);
        dest.writeByte((byte) (isPrimary ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHealthProvider() {
        return healthProvider;
    }

    public void setHealthProvider(String healthProvider) {
        this.healthProvider = healthProvider;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
