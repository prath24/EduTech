package com.example.edutech.Models;

public class WeeklyActivity {
    String activity_name,status;

    public WeeklyActivity() {
    }

    public WeeklyActivity(String activity_name, String status) {
        this.activity_name = activity_name;
        this.status = status;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
