package com.example.user.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.orderfood.DAO.BanAnDAO;

/**
 * Created by USER on 9/9/2016.
 */
public class ThemBanAn extends AppCompatActivity implements View.OnClickListener {
    private EditText edtenban;
    private Button btnDongy;
    BanAnDAO banAnDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembanan);
        edtenban = (EditText) findViewById(R.id.edthembanan);
        btnDongy = (Button) findViewById(R.id.BtnTBADongY);
        banAnDAO = new BanAnDAO(this);
        btnDongy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sTenbanan = edtenban.getText().toString();
        if (sTenbanan != null && (!sTenbanan.equals(""))) {
            boolean kiemtra = banAnDAO.ThemBanAn(sTenbanan);
            Intent intent = new Intent();
            intent.putExtra("ketquathembanan", kiemtra);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
