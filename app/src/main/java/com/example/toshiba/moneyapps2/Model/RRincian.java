package com.example.toshiba.moneyapps2.Model;

public class RRincian {
    private int idrincian;
    private String judulrincian;
    private int uang;
    private String statusrincian;
    private String deskripsirincian;

    public RRincian(int idrincian, String judulrincian, int uang, String statusrincian, String deskripsirincian) {
        this.idrincian = idrincian;
        this.judulrincian = judulrincian;
        this.uang = uang;
        this.statusrincian = statusrincian;
        this.deskripsirincian = deskripsirincian;
    }

    public int getIdrincian() {
        return idrincian;
    }

    public void setIdrincian(int idrincian) {
        this.idrincian = idrincian;
    }

    public String getJudulrincian() {
        return judulrincian;
    }

    public void setJudulrincian(String judulrincian) {
        this.judulrincian = judulrincian;
    }

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public String getStatusrincian() {
        return statusrincian;
    }

    public void setStatusrincian(String statusrincian) {
        this.statusrincian = statusrincian;
    }

    public String getDeskripsirincian() {
        return deskripsirincian;
    }

    public void setDeskripsirincian(String deskripsirincian) {
        this.deskripsirincian = deskripsirincian;
    }
}
