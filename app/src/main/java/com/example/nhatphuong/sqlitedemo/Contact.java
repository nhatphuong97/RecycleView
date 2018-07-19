package com.example.nhatphuong.sqlitedemo;

public class Contact {
    private int mID;
    private String mName;
    private String mPhone;
    private String mAddress;

    public Contact(int ID, String name, String phone, String address) {
        mID = ID;
        mName = name;
        mPhone = phone;
        mAddress = address;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}
