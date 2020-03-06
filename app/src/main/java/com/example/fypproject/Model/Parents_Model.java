package com.example.fypproject.Model;

public class Parents_Model {
    String feedback_father_name, feedbac_cnic, feedbac_family_number,
            feedback_address, feedback_date, feedback_decrition, feedbac_status;


    public Parents_Model() {
    }

    public Parents_Model(String feedback_father_name, String feedbac_cnic, String feedbac_family_number,
                         String feedback_address, String feedback_date, String feedback_decrition,
                         String feedbac_status) {
        this.feedback_father_name = feedback_father_name;
        this.feedbac_cnic = feedbac_cnic;
        this.feedbac_family_number = feedbac_family_number;
        this.feedback_address = feedback_address;
        this.feedback_date = feedback_date;
        this.feedback_decrition = feedback_decrition;
        this.feedbac_status = feedbac_status;
    }

    public String getFeedback_father_name() {
        return feedback_father_name;
    }

    public void setFeedback_father_name(String feedback_father_name) {
        this.feedback_father_name = feedback_father_name;
    }

    public String getFeedbac_cnic() {
        return feedbac_cnic;
    }

    public void setFeedbac_cnic(String feedbac_cnic) {
        this.feedbac_cnic = feedbac_cnic;
    }

    public String getFeedbac_family_number() {
        return feedbac_family_number;
    }

    public void setFeedbac_family_number(String feedbac_family_number) {
        this.feedbac_family_number = feedbac_family_number;
    }

    public String getFeedback_address() {
        return feedback_address;
    }

    public void setFeedback_address(String feedback_address) {
        this.feedback_address = feedback_address;
    }

    public String getFeedback_date() {
        return feedback_date;
    }

    public void setFeedback_date(String feedback_date) {
        this.feedback_date = feedback_date;
    }

    public String getFeedback_decrition() {
        return feedback_decrition;
    }

    public void setFeedback_decrition(String feedback_decrition) {
        this.feedback_decrition = feedback_decrition;
    }

    public String getFeedbac_status() {
        return feedbac_status;
    }

    public void setFeedbac_status(String feedbac_status) {
        this.feedbac_status = feedbac_status;
    }
}