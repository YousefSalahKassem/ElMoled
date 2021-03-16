package com.elmoledmol.www;

import android.os.Parcel;
import android.os.Parcelable;

public class searchinheret implements Parcelable {
    String brand;
Integer logo;
    public searchinheret(Integer logo, String brand) {
        this.logo = logo;
        this.brand = brand;
    }

    protected searchinheret(Parcel in) {
        brand = in.readString();
        if (in.readByte() == 0) {
            logo = null;
        } else {
            logo = in.readInt();
        }
    }

    public static final Creator<searchinheret> CREATOR = new Creator<searchinheret>() {
        @Override
        public searchinheret createFromParcel(Parcel in) {
            return new searchinheret(in);
        }

        @Override
        public searchinheret[] newArray(int size) {
            return new searchinheret[size];
        }
    };

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        if (logo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(logo);
        }
    }
}
