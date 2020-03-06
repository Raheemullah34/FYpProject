package com.example.fypproject.Model;

public class DutiesModel {

    String  duty_team_Name, duty_city, duty_address, duty_date, status,duty_star,duty_end,id,duty_des;

    public DutiesModel() {
    }

    public DutiesModel(String duty_team_Name, String duty_city, String duty_address,
                       String duty_date, String status, String duty_star, String duty_end,
                       String id,String duty_des) {
        this.duty_team_Name = duty_team_Name;
        this.duty_city = duty_city;
        this.duty_address = duty_address;
        this.duty_date = duty_date;
        this.status = status;
        this.duty_star = duty_star;
        this.duty_end = duty_end;
        this.id = id;
        this.duty_des = duty_des;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuty_des() {
        return duty_des;
    }

    public void setDuty_des(String duty_des) {
        this.duty_des = duty_des;
    }

    public String getDuty_team_Name() {
        return duty_team_Name;
    }

    public void setDuty_team_Name(String duty_team_Name) {
        this.duty_team_Name = duty_team_Name;
    }

    public String getDuty_city() {
        return duty_city;
    }

    public void setDuty_city(String duty_city) {
        this.duty_city = duty_city;
    }

    public String getDuty_address() {
        return duty_address;
    }

    public void setDuty_address(String duty_address) {
        this.duty_address = duty_address;
    }

    public String getDuty_date() {
        return duty_date;
    }

    public void setDuty_date(String duty_date) {
        this.duty_date = duty_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDuty_star() {
        return duty_star;
    }

    public void setDuty_star(String duty_star) {
        this.duty_star = duty_star;
    }

    public String getDuty_end() {
        return duty_end;
    }

    public void setDuty_end(String duty_end) {
        this.duty_end = duty_end;
    }
}