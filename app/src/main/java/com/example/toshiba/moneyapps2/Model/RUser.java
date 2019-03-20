package com.example.toshiba.moneyapps2.Model;

public class RUser {
    private int iduser;
    private String nama;
    private String username;
    private int uang;


    public RUser(int iduser, String nama, String username, int uang) {
        this.iduser = iduser;
        this.nama = nama;
        this.username = username;
        this.uang = uang;
    }

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
