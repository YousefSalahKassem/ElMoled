package com.elmoledmol.www;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;

public class newsinheret implements Parcelable  {
    String product;
    float price;
    String hiddentext;
    String imagemodel, logo;
    int mainid, id, brandid, percentage;
    int rate, colorId;
    String color;
    int colorid;
    Integer colorcode;
    List<ViewModel> list;
    List<String> list2;

    public newsinheret(String product, String logo, int brandid) {
        this.product = product;
        this.logo = logo;
        this.brandid = brandid;
    }

    public Integer getColorcode() {
        return colorcode;
    }

    public void setColorcode(Integer colorcode) {
        this.colorcode = colorcode;
    }

    public newsinheret(int colorid, Integer colorcode, List<ViewModel> list, List<String> list2) {
        this.colorid = colorid;
        this.colorcode = colorcode;
        this.list = list;
        this.list2 = list2;
    }

    public List<ViewModel> getList() {
        return list;
    }

    public void setList(List<ViewModel> list) {
        this.list = list;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public newsinheret(int colorId) {
        this.colorId = colorId;
    }

    public newsinheret(int colorId, String color) {
        this.colorId = colorId;
        this.color = color;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public newsinheret(String product, float price, String hiddentext, String imagemodel, String logo) {
        this.product = product;
        this.price = price;
        this.hiddentext = hiddentext;
        this.imagemodel = imagemodel;
        this.logo = logo;
    }

    public newsinheret(String product, float price, String hiddentext, String imagemodel, String logo, int mainid, int id, int brandid, int percentage, int rate) {
        this.product = product;
        this.price = price;
        this.hiddentext = hiddentext;
        this.imagemodel = imagemodel;
        this.logo = logo;
        this.mainid = mainid;
        this.id = id;
        this.brandid = brandid;
        this.percentage = percentage;
        this.rate = rate;
    }

    protected newsinheret(Parcel in) {
        product = in.readString();
        price = in.readFloat();
        hiddentext = in.readString();
        imagemodel = in.readString();
        logo = in.readString();
        mainid = in.readInt();
        id = in.readInt();
        brandid = in.readInt();
        percentage = in.readInt();
        rate = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product);
        dest.writeFloat(price);
        dest.writeString(hiddentext);
        dest.writeString(imagemodel);
        dest.writeString(logo);
        dest.writeInt(mainid);
        dest.writeInt(id);
        dest.writeInt(brandid);
        dest.writeInt(percentage);
        dest.writeInt(rate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<newsinheret> CREATOR = new Parcelable.Creator<newsinheret>() {
        @Override
        public newsinheret createFromParcel(Parcel in) {
            return new newsinheret(in);
        }

        @Override
        public newsinheret[] newArray(int size) {
            return new newsinheret[size];
        }
    };

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getHiddentext() {
        return hiddentext;
    }

    public void setHiddentext(String hiddentext) {
        this.hiddentext = hiddentext;
    }

    public String getImagemodel() {
        return imagemodel;
    }

    public void setImagemodel(String imagemodel) {
        this.imagemodel = imagemodel;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getMainid() {
        return mainid;
    }

    public void setMainid(int mainid) {
        this.mainid = mainid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}