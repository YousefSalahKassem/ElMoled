package com.elmoledmol.www;

public class ordermodel {
    int productid, quantity;

    public ordermodel(int productid, int quantity) {
        this.productid = productid;
        this.quantity = quantity;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
