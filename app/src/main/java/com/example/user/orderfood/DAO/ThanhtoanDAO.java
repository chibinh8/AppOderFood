package com.example.user.orderfood.DAO;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.orderfood.DTO.ThanhtoanDTO;
import com.example.user.orderfood.Database.CreateDatabase;
import com.example.user.orderfood.Thanhtoan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 10/11/2016.
 */
public class ThanhtoanDAO {
    int soluong, mamon;
    int giatien;
    String tenmonan;
    SQLiteDatabase database;
    List<ThanhtoanDTO> thanhtoanDTOs;
    public ThanhtoanDAO(Context context) {
        thanhtoanDTOs = new ArrayList<ThanhtoanDTO>();
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    private void LayDSSoluongVaMamon(int magoimon) {

        String truyvan = "SELECT * FROM " + CreateDatabase.TB_CTGOIMON + " WHERE " + CreateDatabase.TB_CTGOIMON_MAGOIMON + " ='" + magoimon + "'";
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ThanhtoanDTO thanhtoanDTO = new ThanhtoanDTO();
            soluong = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CTGOIMON_SOLUONG));
            mamon = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CTGOIMON_MAMONAN));
            thanhtoanDTO.setMamonan(mamon);
            thanhtoanDTO.setSoluong(soluong);
            thanhtoanDTOs.add(thanhtoanDTO);
            cursor.moveToNext();
        }
    }

    private void LayChitietMonan(){

        for(int i=0; i<thanhtoanDTOs.size();i++) {

            String truyvan = "SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MAMONAN + " ='" + thanhtoanDTOs.get(i).getMamonan() + "'";
            Cursor cursor = database.rawQuery(truyvan, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ThanhtoanDTO thanhtoanDTO = new ThanhtoanDTO();
                giatien = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN));
                tenmonan = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN));
                thanhtoanDTO.setTenmonan(tenmonan);
                thanhtoanDTO.setGiatien(giatien);
                thanhtoanDTO.setSoluong(thanhtoanDTOs.get(i).getSoluong());
                thanhtoanDTO.setMamonan(thanhtoanDTOs.get(i).getMamonan());
                thanhtoanDTOs.set(i,thanhtoanDTO);
                cursor.moveToNext();
            }
            
        }
    }

    public List<ThanhtoanDTO>  LayDsThanhtoan(int magoimon){
        LayDSSoluongVaMamon(magoimon);
        LayChitietMonan();
        return  thanhtoanDTOs;
    }

}


