package com.elmoledmol.www;

public class checkoutinheret {
    String product,price;
    Integer image;

    public checkoutinheret(String product, String price, Integer image) {
        this.product = product;
        this.price = price;
        this.image = image;
    }

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

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
