package com.elmoledmol.www;

public class BrandModel {
    Integer ID;
    String brandName;

    public BrandModel(Integer ID, String brandName) {
        this.ID = ID;
        this.brandName = brandName;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
