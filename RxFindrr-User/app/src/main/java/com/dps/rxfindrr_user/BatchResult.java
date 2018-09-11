package com.dps.rxfindrr_user;

public class BatchResult {
    private String strPrescription;
    private String strBrName;
    private String strQuantity;

    public BatchResult (String strPrescription, String strBrName, String strQuantity){
        this.strPrescription = strPrescription;
        this.strBrName = strBrName;
        this.strQuantity = strQuantity;
    }

    public String getStrPrescription() {
        return strPrescription;
    }

    public void setStrPrescription(String strPrescription) {
        this.strPrescription = strPrescription;
    }

    public void setStrBrName(String strBrName) {
        this.strBrName = strBrName;
    }

    public String getStrBrName() {
        return strBrName;
    }

    public void setStrQuantity(String strQuantity) {
        this.strQuantity = strQuantity;
    }

    public String getStrQuantity() {
        return strQuantity;
    }


}
