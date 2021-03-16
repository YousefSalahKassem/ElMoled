package com.elmoledmol.www;

public class adressinheret {
    String addresstype,details;

    public adressinheret(String addresstype, String details) {
        this.addresstype = addresstype;
        this.details = details;
    }

    public String getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(String addresstype) {
        this.addresstype = addresstype;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
