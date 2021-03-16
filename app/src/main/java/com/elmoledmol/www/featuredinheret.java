package com.elmoledmol.www;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class featuredinheret implements Parcelable {
    String product,price,hiddenprice;
    int imagemodel;
    public featuredinheret(String product, String price, String hiddenprice, int imagemodel) {
        this.product = product;
        this.price = price;
        this.hiddenprice = hiddenprice;
        this.imagemodel = imagemodel;
    }

    protected featuredinheret(Parcel in) {
        product = in.readString();
        price = in.readString();
        hiddenprice = in.readString();
        imagemodel = in.readInt();
    }

    public static final Creator<featuredinheret> CREATOR = new Creator<featuredinheret>() {
        @Override
        public featuredinheret createFromParcel(Parcel in) {
            return new featuredinheret(in);
        }

        @Override
        public featuredinheret[] newArray(int size) {
            return new featuredinheret[size];
        }
    };

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHiddenprice() {
        return hiddenprice;
    }

    public void setHiddenprice(String hiddenprice) {
        this.hiddenprice = hiddenprice;
    }

    public int getImagemodel() {
        return imagemodel;
    }

    public void setImagemodel(int imagemodel) {
        this.imagemodel = imagemodel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product);
        dest.writeString(price);
        dest.writeString(hiddenprice);
        dest.writeInt(imagemodel);
    }
}
