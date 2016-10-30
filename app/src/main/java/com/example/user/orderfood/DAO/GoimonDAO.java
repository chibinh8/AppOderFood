package com.example.user.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.orderfood.DTO.GoimonDTO;
import com.example.user.orderfood.Database.CreateDatabase;

/**
 * Created by USER on 10/9/2016.
 */
public class GoimonDAO {
    SQLiteDatabase database;
    public GoimonDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();


    }

    public boolean ThemGoimon(GoimonDTO goimonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_GOIMON_MABAN,goimonDTO.getMaban());
        contentValues.put(CreateDatabase.TB_GOIMON_MANV,goimonDTO.getMaNV());
        contentValues.put(CreateDatabase.TB_GOIMON_NGAYGOI,goimonDTO.getNgaygoi());
        contentValues.put(CreateDatabase.TB_GOIMON_TINHTRANG,goimonDTO.getTinhtrang());
        long kiemtra = database.insert(CreateDatabase.TB_GOIMON,null,contentValues);
        if(kiemtra!=0)
            return true;
        else
            return false;

    }
    public int LayMaGoimon(int maban, String tinhtrang){
        int magoimon = 0;
        String truyvan = "SELECT * FROM " + CreateDatabase.TB_GOIMON + " WHERE " + CreateDatabase.TB_GOIMON_MABAN +" ='"+maban+"' AND " + CreateDatabase.TB_GOIMON_TINHTRANG+ "='"+ tinhtrang+"'";
        Cursor cursor= database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            magoimon = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_MAGOIMON));
            cursor.moveToNext();
        }
        return  magoimon;
    }

    public boolean CapnhatTinhtrangGoimon(int magoimon, String tinhtrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_GOIMON_TINHTRANG,tinhtrang);
        long kiemtra = database.update(CreateDatabase.TB_GOIMON, contentValues,CreateDatabase.TB_GOIMON_MAGOIMON + " ='"+ magoimon+"'",null);
        if(kiemtra==0)
            return false;
        else
            return true;
    }


}
