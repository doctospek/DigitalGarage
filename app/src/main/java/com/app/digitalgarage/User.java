package com.app.digitalgarage;/* Created By Ashwini Saraf on 3/31/2021*/

import java.io.Serializable;

public class User implements Serializable {

    String id, fname, lnme, password, chesis_no, car_no, year, birthday, mobile_no, gender, permanent_address, present_address;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLnme() {
        return lnme;
    }

    public void setLnme(String lnme) {
        this.lnme = lnme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChesis_no() {
        return chesis_no;
    }

    public void setChesis_no(String chesis_no) {
        this.chesis_no = chesis_no;
    }

    public String getCar_no() {
        return car_no;
    }

    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getPresent_address() {
        return present_address;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", fname='" + fname + '\'' +
                ", lnme='" + lnme + '\'' +
                ", password='" + password + '\'' +
                ", chesis_no='" + chesis_no + '\'' +
                ", car_no='" + car_no + '\'' +
                ", year='" + year + '\'' +
                ", birthday='" + birthday + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", gender='" + gender + '\'' +
                ", permanent_address='" + permanent_address + '\'' +
                ", present_address='" + present_address + '\'' +
                '}';
    }
}
