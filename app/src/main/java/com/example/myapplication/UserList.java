package com.example.myapplication;

public class UserList {
    public Integer ID;
    public String Name;
    public String Sex;
    public String Dob;
    public String Phone;

    public UserList(Integer ID, String Name, String Sex, String Dob, String Phone){
        this.ID = ID;
        this.Name = Name;
        this.Sex = Sex;
        this.Dob = Dob;
        this.Phone = Phone;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
