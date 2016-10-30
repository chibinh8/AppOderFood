package com.example.user.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.user.orderfood.DTO.BanAnDTO;
import com.example.user.orderfood.DTO.LoaiMonanDTO;
import com.example.user.orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 9/13/2016.
 */
public class LoaiMonanDAO{
    SQLiteDatabase database;
    public LoaiMonanDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();

    }

    public boolean ThemLoaimonan(String tenloai, String Hinhanh){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_LOAIMONAN_TENLOAI,tenloai);
        contentValues.put(CreateDatabase.TB_LOAIMONAN_HINHANH,Hinhanh);
        long kiemtra = database.insert(CreateDatabase.TB_LOAIMONAN,null,contentValues);
        if(kiemtra!=0)
            return true;
        else
            return false;

    }

    public List<LoaiMonanDTO> LayDSMonan(){
        LoaiMonanDTO loaiMonanDTO;
        List<LoaiMonanDTO> ListMonanDTO = new ArrayList<LoaiMonanDTO>();
        String truyvan =  "SELECT * FROM "+CreateDatabase.TB_LOAIMONAN;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!(cursor.isAfterLast())){
            loaiMonanDTO = new LoaiMonanDTO();
            loaiMonanDTO.setMaloai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_MALOAI)));
            loaiMonanDTO.setTenloai(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_TENLOAI)));
            ListMonanDTO.add(loaiMonanDTO);
            cursor.moveToNext();
        }
        return ListMonanDTO;

    }
    public String LayHinhAnh(int MaLoai){
        String SPathHinh="";
        String Truyvan = "SELECT " + CreateDatabase.TB_MONAN_HINHANH + " FROM "+ CreateDatabase.TB_MONAN  +" WHERE " + CreateDatabase.TB_MONAN_MALOAI+ "= " +  Integer.toString(MaLoai) + " AND " + CreateDatabase.TB_MONAN_HINHANH + "!=\"\"" +" ORDER BY "+CreateDatabase.TB_MONAN_MAMONAN +" DESC LIMIT 1" ;
        Cursor cursor = database.rawQuery(Truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            SPathHinh = cursor.getString(cursor.getColumnIndex((CreateDatabase.TB_MONAN_HINHANH)));
            cursor.moveToNext();
        }

        return SPathHinh;

    }

}
