package com.example.nhatphuong.contentprovinder_demo_getcontact;

public class Contact {
    public String mName, mPhone;

    public Contact(String name, String phone) {
        mName = name;
        mPhone = phone;
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
}