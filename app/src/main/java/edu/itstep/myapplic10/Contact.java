package edu.itstep.myapplic10;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import android.os.Parcel;
import android.os.Parcelable;


@Entity(foreignKeys = @ForeignKey(entity = Address.class, parentColumns = "id", childColumns = "addressId"))
public class Contact implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "full_name")
    private String fullName;

    @ColumnInfo(name = "phone_number")
    private String phone;

    @ColumnInfo(name = "addressId", index = true)
    private long addressId;

    // Constructor
    public Contact(String fullName, String phone, long addressId) {
        this.fullName = fullName;
        this.phone = phone;
        this.addressId = addressId;
    }

    // Parcelable implementation
    protected Contact(Parcel in) {
        id = in.readInt();
        fullName = in.readString();
        phone = in.readString();
        addressId = in.readLong();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fullName);
        dest.writeString(phone);
        dest.writeLong(addressId);
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}

