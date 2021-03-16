package com.elmoledmol.www;

import java.util.List;

public class requestmodel {
int phone;
String address;
List<ordermodel>list;

    public requestmodel(int phone, String address, List<ordermodel> list) {
        this.phone = phone;
        this.address = address;
        this.list = list;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ordermodel> getList() {
        return list;
    }

    public void setList(List<ordermodel> list) {
        this.list = list;
    }
}
