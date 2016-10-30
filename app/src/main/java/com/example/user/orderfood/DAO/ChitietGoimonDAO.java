package com.example.user.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.orderfood.DTO.ChitietGoimonDTO;
import com.example.user.orderfood.Database.CreateDatabase;

/**
 * Created by USER on 10/9/2016.
 */
public class ChitietGoimonDAO {
    SQLiteDatabase database;
    public ChitietGoimonDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemChitietGoimon(ChitietGoimonDTO chitietGoimonDTO){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CTGOIMON_MAGOIMON, chitietGoimonDTO.getMagoimon());
        contentValues.put(CreateDatabase.TB_CTGOIMON_MAMONAN, chitietGoimonDTO.getMamonan());
        contentValues.put(CreateDatabase.TB_CTGOIMON_SOLUONG, chitietGoimonDTO.getSoluong());
        long kiemtra = database.insert(CreateDatabase.TB_CTGOIMON,null,contentValues);
        if(kiemtra!=0)
            return true;
        else
            return false;

    }

    public int KiemtraMonanTonTai(int magoimon, int mamonan){
        int soluong;
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_CTGOIMON + " WHERE " + CreateDatabase.TB_CTGOIMON_MAGOIMON + " ='" +
                magoimon + "' AND " + CreateDatabase.TB_CTGOIMON_MAMONAN + " ='" + mamonan + "'";
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        if (cursor.getCount()!=0)
            return cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CTGOIMON_SOLUONG));
        else
            return 0;
    }

    public boolean CapnhatSoluong(int magoimon, int mamonan, int soluong){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CTGOIMON_SOLUONG,soluong);
        long kiemtra = database.update(CreateDatabase.TB_CTGOIMON, contentValues,CreateDatabase.TB_CTGOIMON_MAGOIMON + " ='"+ magoimon+"' AND " + CreateDatabase.TB_CTGOIMON_MAMONAN+"='"+mamonan+"'",null);
        if(kiemtra==0)
            return false;
        else
            return true;

    }

}
