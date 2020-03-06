package com.example.fypproject.Model;

public class ChildModel {
    String child_name,child_father_name,child_family_no,child_location,father_cnic,child_reg_date,entery_person_type;

    public ChildModel() {
    }

    public ChildModel(String child_name, String child_father_name, String child_family_no,
                      String child_location, String father_cnic, String child_reg_date,
                      String entery_person_type) {
        this.child_name = child_name;
        this.child_father_name = child_father_name;
        this.child_family_no = child_family_no;
        this.child_location = child_location;
        this.father_cnic = father_cnic;
        this.child_reg_date = child_reg_date;
        this.entery_person_type = entery_person_type;
    }

    public String getChild_name() {
        return child_name;
    }

    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }

    public String getChild_father_name() {
        return child_father_name;
    }

    public void setChild_father_name(String child_father_name) {
        this.child_father_name = child_father_name;
    }

    public String getChild_family_no() {
        return child_family_no;
    }

    public void setChild_family_no(String child_family_no) {
        this.child_family_no = child_family_no;
    }

    public String getChild_location() {
        return child_location;
    }

    public void setChild_location(String child_location) {
        this.child_location = child_location;
    }

    public String getFather_cnic() {
        return father_cnic;
    }

    public void setFather_cnic(String father_cnic) {
        this.father_cnic = father_cnic;
    }

    public String getChild_reg_date() {
        return child_reg_date;
    }

    public void setChild_reg_date(String child_reg_date) {
        this.child_reg_date = child_reg_date;
    }

    public String getEntery_person_type() {
        return entery_person_type;
    }

    public void setEntery_person_type(String entery_person_type) {
        this.entery_person_type = entery_person_type;
    }
}
