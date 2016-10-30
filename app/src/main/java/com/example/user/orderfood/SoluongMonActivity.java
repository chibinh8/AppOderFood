package com.example.user.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.orderfood.DAO.ChitietGoimonDAO;
import com.example.user.orderfood.DAO.GoimonDAO;
import com.example.user.orderfood.DTO.ChitietGoimonDTO;

/**
 * Created by USER on 10/9/2016.
 */
public class SoluongMonActivity extends AppCompatActivity implements View.OnClickListener{
    Button btndongy;
    EditText edsoluong;
    int maban, mamonan;
    int soluongmonan;
    GoimonDAO goimonDAO;
    int magoimon;
    ChitietGoimonDTO chitietGoimonDTO;
    ChitietGoimonDAO chitietGoimonDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_soluongmonan);
        btndongy = (Button) findViewById(R.id.BtnSLDongY);
        edsoluong = (EditText) findViewById(R.id.edsoluongmonan);
        Intent intent = getIntent();
        maban = intent.getIntExtra("maban",0);
        mamonan = intent.getIntExtra("mamonan",0);
        goimonDAO = new GoimonDAO(this);
        chitietGoimonDAO = new ChitietGoimonDAO(this);
        if(maban!=0) {
            magoimon = goimonDAO.LayMaGoimon(maban, "False");
            btndongy.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.BtnSLDongY:
                if(maban!=0) {

                    soluongmonan = Integer.parseInt(edsoluong.getText().toString());
                    chitietGoimonDTO = new ChitietGoimonDTO();
                    chitietGoimonDTO.setMagoimon(magoimon);
                    chitietGoimonDTO.setMamonan(mamonan);

                    if (chitietGoimonDAO.KiemtraMonanTonTai(magoimon, mamonan) == 0) {

                        chitietGoimonDTO.setSoluong(soluongmonan);
                        if (!chitietGoimonDAO.ThemChitietGoimon(chitietGoimonDTO))
                            Toast.makeText(this, "Thêm CTGM Thất bại", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(this, "Thêm CTGM Thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        soluongmonan += chitietGoimonDAO.KiemtraMonanTonTai(magoimon, mamonan);
                        chitietGoimonDAO.CapnhatSoluong(magoimon, mamonan, soluongmonan);
                    }
                }
                Log.d("soluong",soluongmonan+"");
                Log.d("maban",maban+"");
                Log.d("magoimon",magoimon+"");
                Log.d("mamonan",mamonan+"");
                finish();
                break;


        }
    }
}
