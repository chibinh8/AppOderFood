package com.example.user.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.user.orderfood.DTO.NhanVienDTO;
import com.example.user.orderfood.Database.CreateDatabase;
import com.example.user.orderfood.SignIn;

/**
 * Created by USER on 8/28/2016.
 */
public class NhanVienDAO {

    SQLiteDatabase database;
    public NhanVienDAO(Context context) {

        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public  long ThemNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_NHANVIEN_TENDN, nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TB_NHANVIEN_CMND, nhanVienDTO.getCMND());
        contentValues.put(CreateDatabase.TB_NHANVIEN_GIOITINH, nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MATKHAU, nhanVienDTO.getMATKHAU());
        contentValues.put(CreateDatabase.TB_NHANVIEN_NGAYSINH, nhanVienDTO.getNGAYSINH());
        long kiemtra = database.insert(CreateDatabase.TB_NHANVIEN, null, contentValues);
        return kiemtra;
    }

    public boolean KiemTraNhanVien(){
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(truyvan, null);
        if(cursor.getCount()!=0)
            return true;
        else
            return false;

    }
    public int KiemTraDangNhap(String tendangnhap, String MatKhau){
        String truyvan = "SELECT * FROM "+ CreateDatabase.TB_NHANVIEN + " WHERE " + CreateDatabase.TB_NHANVIEN_TENDN + " = '" + tendangnhap
                + "' AND " + CreateDatabase.TB_NHANVIEN_MATKHAU + " = '" + MatKhau + "'";
        Cursor cursor = database.rawQuery(truyvan, null);

        String tv = "SELECT " + CreateDatabase.TB_NHANVIEN_TENDN + " FROM "+ CreateDatabase.TB_NHANVIEN;
        Cursor cursortv = database.rawQuery(tv, null);
        String TenDN;
        int MaNV =0;
        if(cursortv.getCount()!=0){
            cursortv.moveToFirst();
            TenDN = cursortv.getString(cursortv.getColumnIndex(CreateDatabase.TB_NHANVIEN_TENDN));
            }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MaNV = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MNV));
            cursor.moveToNext();
        }
        if(cursor.getCount()!=0)
            return MaNV;
        else
            return 0;
    }

}
