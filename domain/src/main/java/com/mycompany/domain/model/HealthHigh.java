package com.mycompany.domain.model;

public class HealthHigh {
    private String status;
    private String appVersion;

    public HealthHigh(){}

    public HealthHigh(String status, String appVersion){
        this.status = status;
        this.appVersion = appVersion;
    }

    public String getStatus() { return this.status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppVersion() { return this.status; }

    public void setAppVersion(String status) {
        this.status = status;
    }
}
