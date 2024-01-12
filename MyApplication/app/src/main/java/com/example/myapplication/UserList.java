package com.example.myapplication;

public class UserList {
    public Integer UserID;
    public String Email;
    public String Name;
    public String Phone;
    public Integer WalletID;

    public UserList(Integer UserID, String Email, String Name, String Phone, Integer WalletID){
        this.UserID = UserID;
        this.Email = Email;
        this.Name = Name;
        this.Phone = Phone;
        this.WalletID = WalletID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Integer getWalletID() {
        return WalletID;
    }

    public void setWalletID(Integer walletID) {
        WalletID = walletID;
    }
}
