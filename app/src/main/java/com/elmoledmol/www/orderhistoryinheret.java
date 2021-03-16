package com.elmoledmol.www;

public class orderhistoryinheret {
    String placed,amount,status,ordernumber;

    public orderhistoryinheret(String placed, String amount, String status, String ordernumber) {
        this.placed = placed;
        this.amount = amount;
        this.status = status;
        this.ordernumber = ordernumber;
    }

    public String getPlaced() {
        return placed;
    }

    public void setPlaced(String placed) {
        this.placed = placed;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }
}
