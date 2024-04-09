package com.dpm.payment.models.cep;

public class ComplaintsModel {

    private String complaintsTitle;



    private int complaintsIcon;

    public ComplaintsModel(String cepTitle, int cepIcon) {
        this.complaintsTitle = cepTitle;
        this.complaintsIcon = cepIcon;
    }
    public String getComplaintsTitle() {
        return complaintsTitle;
    }

    public int getComplaintsIcon() {
        return complaintsIcon;
    }

}
