package com.dps.rxfindrr_user.Model;

public class SampleStoreModel {

    private String brandName;

    public SampleStoreModel() {

    }

    public SampleStoreModel(String brandName) {
        this.brandName = brandName;
    }


    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "SampleStoreModel{" +
                "brandName='" + brandName + '\'' +
                '}';
    }



}
