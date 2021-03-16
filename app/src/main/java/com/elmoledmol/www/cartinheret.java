package com.elmoledmol.www;

import android.os.Parcel;
import android.os.Parcelable;

public class cartinheret implements Parcelable {
    float price;
    String images;
    int Qunatity;
    String name;
    int colorId;
    int sizeId;
    int mainId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public cartinheret(String name, float price, String images, int mainId, int qunatity, int colorId, int choiceSize) {
        this.price = price;
        this.images = images;
        this.Qunatity = qunatity;
        this.name = name;
        this.colorId = colorId;
        this.sizeId = choiceSize;
        this.mainId = mainId;
    }

    protected cartinheret(Parcel in) {
        price = in.readFloat();
        images = in.readString();
        Qunatity = in.readInt();
        name = in.readString();
        colorId = in.readInt();
        sizeId = in.readInt();
        mainId = in.readInt();
    }

    public static final Creator<cartinheret> CREATOR = new Creator<cartinheret>() {
        @Override
        public cartinheret createFromParcel(Parcel in) {
            return new cartinheret(in);
        }

        @Override
        public cartinheret[] newArray(int size) {
            return new cartinheret[size];
        }
    };

    public float getPrice() {
        return price*Qunatity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getQunatity() {
        return Qunatity;
    }

    public void setQunatity(int qunatity) {
        Qunatity = qunatity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(price);
        dest.writeString(images);
        dest.writeInt(Qunatity);
        dest.writeString(name);
        dest.writeInt(colorId);
        dest.writeInt(sizeId);
        dest.writeInt(mainId);

    }
}
