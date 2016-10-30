package com.example.user.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.orderfood.DAO.LoaiMonanDAO;

/**
 * Created by USER on 9/11/2016.
 */
public class ThemLoaiMonan extends AppCompatActivity implements View.OnClickListener{
    EditText EdLoaimon;
    Button BtnDongy;
    LoaiMonanDAO loaiMonanDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themloaimonan);
        EdLoaimon = (EditText) findViewById(R.id.edthemloaimon);
        BtnDongy = (Button) findViewById(R.id.BtnTLMDongY);
        BtnDongy.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.BtnTLMDongY:
                String tenmonan = EdLoaimon.getText().toString();
                if (tenmonan != null && (!tenmonan.equals(""))) {
                    loaiMonanDAO = new LoaiMonanDAO(this);
                    Intent intentGetHinh =getIntent();
                    String pathhinhanh = intentGetHinh.getStringExtra("pathhinhanh");
                    boolean kiemtra = loaiMonanDAO.ThemLoaimonan(tenmonan,pathhinhanh);
                    Intent intent = new Intent();
                    intent.putExtra("kqthemmonan", kiemtra);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                    break;
                }else{
                    Toast.makeText(this,getResources().getString(R.string.Nhaplai), Toast.LENGTH_SHORT).show();
                }

        }
    }
}
