package com.myapp.sanjana.firebasedemo;

import android.net.Uri;

public class DataModel {

    String name="";
    String version="";
    String email="",owner_name="",contact="",desc="",address="";
    Uri image;

    public DataModel(String name, String version, Uri image,String owner_name,String contact,String email,String desc,String address) {
        this.name = name;
        this.version = version;
        this.image=image;
        this.contact=contact;
        this.email=email;
        this.owner_name=owner_name;
        this.desc=desc;
        this.address=address;
    }

    public DataModel(String name,String price,Uri image)
    {
        this.name=name;
        this.version=price;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Uri getImage() {
        return image;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getDesc() {
        return desc;
    }

    public String getAddress() {
        return address;
    }
}
