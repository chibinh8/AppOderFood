package com.example.user.orderfood.DTO;

/**
 * Created by USER on 9/8/2016.
 */
public class BanAnDTO {

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String tenBan) {
        TenBan = tenBan;
    }

    int MaBan;
    String TenBan;

    public boolean GetisDuocchon() {
        return IsDuocchon;
    }

    public void setDuocchon(boolean duocchon) {
        IsDuocchon = duocchon;
    }

    boolean IsDuocchon;

}
