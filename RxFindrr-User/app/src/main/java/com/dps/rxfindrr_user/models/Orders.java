package com.dps.rxfindrr_user.models;

import java.util.List;

public class Orders {
    private String uid;
    private String drugstore;
    private String qrCodeUrl;
    private List<PrescribedMedicine> lPrescribedMedicines;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDrugstore() {
        return drugstore;
    }

    public void setDrugstore(String drugstore) {
        this.drugstore = drugstore;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public List<PrescribedMedicine> getlPrescribedMedicines() {
        return lPrescribedMedicines;
    }

    public void setlPrescribedMedicines(List<PrescribedMedicine> lPrescribedMedicines) {
        this.lPrescribedMedicines = lPrescribedMedicines;
    }
}
