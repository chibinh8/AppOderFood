package com.example.user.orderfood.DAO;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import com.example.user.orderfood.DTO.BanAnDTO;
import com.example.user.orderfood.Database.CreateDatabase;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 9/8/2016.
 */
public class BanAnDAO {
    SQLiteDatabase database;
    public BanAnDAO(Context context) {

        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemBanAn(String tenbanan){
        ContentValues  contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN,tenbanan);
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG,"False");
       long kiemtra =  database.insert(CreateDatabase.TB_BANAN, null,contentValues);
        if(kiemtra!=0)
            return true;
        else
            return false;

    }

    public List<BanAnDTO> LayDanhsachBA(){
        BanAnDTO banAnDTO;
        List<BanAnDTO> ListbananDTO = new ArrayList<BanAnDTO>();
        String truyvan =  "SELECT * FROM "+CreateDatabase.TB_BANAN;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!(cursor.isAfterLast())){
            banAnDTO = new BanAnDTO();
            banAnDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_MABAN)));
            banAnDTO.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TENBAN)));
            ListbananDTO.add(banAnDTO);
            cursor.moveToNext();
        }
        return ListbananDTO;
    }

    public String LayTinhTrangBanan(int maban){
        String tinhtrang = "";
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_BANAN + " WHERE "+ CreateDatabase.TB_BANAN_MABAN + " = '" + maban+ "'";
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tinhtrang = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TINHTRANG));
            cursor.moveToNext();
        }

        return tinhtrang;
    }
    public boolean CapnhatTinhtrang(int maban, String tinhtrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG,tinhtrang);
        long kiemtra = database.update(CreateDatabase.TB_BANAN, contentValues,CreateDatabase.TB_BANAN_MABAN + " ='"+ maban+"'",null);
        if(kiemtra==0)
            return false;
        else
            return true;
    }

}
