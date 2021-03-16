package com.elmoledmol.www;

import android.widget.ImageView;
import android.widget.TextView;

public class ChildItem {
    private int logo, model;
    private String discount, itemName, newPrice, oldPrice;
    private int mainid,percentage,brandid;
    private String productname,imagemodel,logobrand;
    private int price;

    public int getMainid() {
        return mainid;
    }

    public void setMainid(int mainid) {
        this.mainid = mainid;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getImagemodel() {
        return imagemodel;
    }

    public void setImagemodel(String imagemodel) {
        this.imagemodel = imagemodel;
    }

    public String getLogobrand() {
        return logobrand;
    }

    public void setLogobrand(String logobrand) {
        this.logobrand = logobrand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ChildItem(int mainid, int percentage, int brandid, String productname, String imagemodel, String logobrand, int price) {
        this.mainid = mainid;
        this.percentage = percentage;
        this.brandid = brandid;
        this.productname = productname;
        this.imagemodel = imagemodel;
        this.logobrand = logobrand;
        this.price = price;
    }

    public ChildItem(int logo, int model, String discount, String itemName, String newPrice, String oldPrice) {
        this.logo = logo;
        this.model = model;
        this.discount = discount;
        this.itemName = itemName;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }
}
