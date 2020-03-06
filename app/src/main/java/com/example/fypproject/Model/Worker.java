package com.example.fypproject.Model;

public class Worker {

    String workerName, workerContact, workerCnic, workerFatherName, WorkerJobStatus;
    String workerTeam, loginStatus,workerPassword;


    public Worker() {
    }

    public Worker(String workerName, String workerContact, String workerCnic, String workerFatherName,
                  String workerJobStatus, String workerTeam, String loginStatus, String workerPassword) {
        this.workerName = workerName;
        this.workerContact = workerContact;
        this.workerCnic = workerCnic;
        this.workerFatherName = workerFatherName;
        WorkerJobStatus = workerJobStatus;
        this.workerTeam = workerTeam;
        this.loginStatus = loginStatus;
        this.workerPassword = workerPassword;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerContact() {
        return workerContact;
    }

    public void setWorkerContact(String workerContact) {
        this.workerContact = workerContact;
    }

    public String getWorkerCnic() {
        return workerCnic;
    }

    public void setWorkerCnic(String workerCnic) {
        this.workerCnic = workerCnic;
    }

    public String getWorkerFatherName() {
        return workerFatherName;
    }

    public void setWorkerFatherName(String workerFatherName) {
        this.workerFatherName = workerFatherName;
    }

    public String getWorkerJobStatus() {
        return WorkerJobStatus;
    }

    public void setWorkerJobStatus(String workerJobStatus) {
        WorkerJobStatus = workerJobStatus;
    }

    public String getWorkerTeam() {
        return workerTeam;
    }

    public void setWorkerTeam(String workerTeam) {
        this.workerTeam = workerTeam;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getWorkerPassword() {
        return workerPassword;
    }

    public void setWorkerPassword(String workerPassword) {
        this.workerPassword = workerPassword;
    }
}