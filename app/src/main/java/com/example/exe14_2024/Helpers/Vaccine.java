package com.example.exe14_2024.Helpers;
/**
 * @author		Yiftah David yd2058@bs.amalnet.k12.il
 * @version	1.1
 * @since		10/4/2024
 * activity used to mange student vaccines
 */
public class Vaccine {
    private int day, month, year;
    private String location;
    public Vaccine(int day, int month, int year, String location){
        this.day = day;
        this.month = month;
        this.year = year;
        this.location = location;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getLocation() {
        return location;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Vaccine (){}
}
