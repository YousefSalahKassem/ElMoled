package com.elmoledmol.www;

import android.os.Parcel;
import android.os.Parcelable;

public class topinheret implements Parcelable {
    String product,description,price;
            int model,logo;

    public topinheret(String product, String description, String price, Integer model, Integer logo) {
        this.product = product;
        this.description = description;
        this.price = price;
        this.model = model;
        this.logo = logo;
    }

    protected topinheret(Parcel in) {
        product = in.readString();
        description = in.readString();
        price = in.readString();
        model = in.readInt();
        logo = in.readInt();
    }

    public static final Creator<topinheret> CREATOR = new Creator<topinheret>() {
        @Override
        public topinheret createFromParcel(Parcel in) {
            return new topinheret(in);
        }

        @Override
        public topinheret[] newArray(int size) {
            return new topinheret[size];
        }
    };

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeInt(model);
        dest.writeInt(logo);
    }
}
