package com.example.user.orderfood.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 8/25/2016.
 */
public class CreateDatabase extends SQLiteOpenHelper {

    public static String TB_NHANVIEN = "NHANVIEN";
    public static String TB_MONAN = "MONAN";
    public static String TB_BANAN = "BANAN";
    public static String TB_GOIMON = "GOIMON";
    public static String TB_CTGOIMON = "CTGOIMON";
    public static String TB_LOAIMONAN = "LOAIMONAN";

    public static String TB_NHANVIEN_MNV = "MNV";
    public static String TB_NHANVIEN_TENDN = "TENDN";
    public static String TB_NHANVIEN_MATKHAU = "MATKHAU";
    public static String TB_NHANVIEN_GIOITINH = "GIOITINH";
    public static String TB_NHANVIEN_NGAYSINH = "NGAYSINH";
    public static String TB_NHANVIEN_CMND = "CMND";

    public static String TB_MONAN_TENMONAN = "TENMONAN";
    public static String TB_MONAN_MAMONAN = "MAMONAN";
    public static String TB_MONAN_GIATIEN = "GIATIEN";
    public static String TB_MONAN_MALOAI = "MALOAI";
    public static String TB_MONAN_HINHANH = "HINHANH";

    public static String TB_BANAN_MABAN = "MABAN";
    public static String TB_BANAN_TENBAN = "TENBAN";
    public static String TB_BANAN_TINHTRANG = "TINHTRANG";

    public static String TB_GOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_GOIMON_MANV = "MANV";
    public static String TB_GOIMON_NGAYGOI = "NGAYGOI";
    public static String TB_GOIMON_TINHTRANG = "TINHTRANG";
    public static String TB_GOIMON_MABAN = "MABAN";

    public static String TB_CTGOIMON_MAGOIMON = "MAGOIMON";
    public static String TB_CTGOIMON_MAMONAN = "MAMONAN";
    public static String TB_CTGOIMON_SOLUONG = "SOLUONG";

    public static String TB_LOAIMONAN_MALOAI = "MALOAI";
    public static String TB_LOAIMONAN_TENLOAI = "TENLOAI";
    public static String TB_LOAIMONAN_HINHANH = "HINHANH";
    public CreateDatabase(Context context) {
        super(context, "OrderFood", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tbNHANVIEN = "CREATE TABLE "+TB_NHANVIEN + "("+TB_NHANVIEN_MNV + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TB_NHANVIEN_TENDN + " TEXT, "
                + TB_NHANVIEN_MATKHAU + " TEXT, " + TB_NHANVIEN_GIOITINH + " TEXT, "+ TB_NHANVIEN_NGAYSINH + " TEXT, "+ TB_NHANVIEN_CMND + " INTEGER ) ";

        String tbMONAN = "CREATE TABLE "+TB_MONAN + "("+TB_MONAN_MAMONAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TB_MONAN_MALOAI + " INTEGER, "  + TB_MONAN_GIATIEN + " INTEGER, "
        + TB_MONAN_TENMONAN + " INTEGER, " + TB_MONAN_HINHANH + " TEXT ) ";

        String tbBANAN = "CREATE TABLE "+TB_BANAN + "("+TB_BANAN_MABAN + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TB_BANAN_TENBAN + " TEXT, "
                + TB_BANAN_TINHTRANG + " TEXT ) ";

        String tbGOIMON = "CREATE TABLE "+TB_GOIMON + "("+TB_GOIMON_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TB_GOIMON_MANV + " INTEGER, "
                + TB_GOIMON_NGAYGOI + " TEXT," + TB_GOIMON_TINHTRANG + " TEXT, " + TB_GOIMON_MABAN + " INTEGER) ";

        String tbLOAIMONAN = "CREATE TABLE "+TB_LOAIMONAN + "("+TB_LOAIMONAN_MALOAI + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TB_LOAIMONAN_TENLOAI + " TEXT,  " + TB_LOAIMONAN_HINHANH + " TEXT ) ";

        String tbCTGOIMON = "CREATE TABLE "+TB_CTGOIMON + " ( "+TB_CTGOIMON_MAGOIMON + " INTEGER, " + TB_CTGOIMON_MAMONAN + " INTEGER, "
            + TB_CTGOIMON_SOLUONG + " INTEGER, " + "PRIMARY KEY ("+ TB_CTGOIMON_MAGOIMON + "," + TB_CTGOIMON_MAMONAN + "))" ;


        db.execSQL(tbNHANVIEN);
        db.execSQL(tbMONAN);
        db.execSQL(tbLOAIMONAN);
        db.execSQL(tbBANAN);
        db.execSQL(tbGOIMON);
        db.execSQL(tbCTGOIMON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase open() {
       return this.getWritableDatabase();
    }
}
