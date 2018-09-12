package com.dps.rxfindrr_user.Model;

import android.widget.TextView;

import org.w3c.dom.Text;

public class StoreModel {
    private int image, edit, delete;
    private String StoreName, Brand, quantity;


    public void StoreModel() {

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getEdit() {
        return edit;
    }

    public void setEdit(int edit) {
        this.edit = edit;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StoreModel{" +
                "image=" + image +
                ", edit=" + edit +
                ", delete=" + delete +
                ", StoreName='" + StoreName + '\'' +
                ", Brand='" + Brand + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }

    public StoreModel(int image, int edit, int delete, String storeName, String brand, String quantity) {
        this.image = image;
        this.edit = edit;
        this.delete = delete;
        StoreName = storeName;
        Brand = brand;
        this.quantity = quantity;
    }


}
