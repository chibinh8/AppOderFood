package com.example.user.orderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.orderfood.DTO.LoaiMonanDTO;
import com.example.user.orderfood.DTO.MonanDTO;
import com.example.user.orderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 9/15/2016.
 */
public class MonanDAO {

    SQLiteDatabase database;
    MonanDTO monanDTO;
    public MonanDAO(Context context) {

        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemMonan(MonanDTO monanDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN,monanDTO.getTenmonan());
        contentValues.put(CreateDatabase.TB_MONAN_MALOAI,monanDTO.getMaloai());
        contentValues.put(CreateDatabase.TB_MONAN_GIATIEN,monanDTO.getGiatien());
        contentValues.put(CreateDatabase.TB_MONAN_HINHANH,monanDTO.getHinhanh());
        long kiemtra =  database.insert(CreateDatabase.TB_MONAN, null,contentValues);
        if(kiemtra!=0)
            return true;
        else
            return false;


    }

    public List<MonanDTO> LayDSMonan(int maloai){
        List<MonanDTO> monanDTOList = new ArrayList<MonanDTO>();
        String truyvan =  "SELECT * FROM "+CreateDatabase.TB_MONAN + " WHERE "+ CreateDatabase.TB_MONAN_MALOAI+ " = "+ maloai;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!(cursor.isAfterLast())){
            monanDTO = new MonanDTO();
            monanDTO.setTenmonan((cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN))));
            monanDTO.setGiatien((cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN))));
            monanDTO.setMaloai((cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MALOAI))));
            monanDTO.setHinhanh((cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH))));
            monanDTO.setMamonan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMONAN)));
            monanDTOList.add(monanDTO);
            cursor.moveToNext();
        }
        return monanDTOList;

    }


}
