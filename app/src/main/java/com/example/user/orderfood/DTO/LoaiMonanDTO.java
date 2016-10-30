package com.example.user.orderfood.DTO;

/**
 * Created by USER on 9/13/2016.
 */
public class LoaiMonanDTO {

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }



    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    String HinhAnh;
    String tenloai;
    int maloai;
}
