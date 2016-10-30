package com.example.user.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.orderfood.DAO.NhanVienDAO;

/**
 * Created by USER on 9/3/2016.
 */
public class SignIn extends AppCompatActivity implements View.OnClickListener{
    Button BtnDK, BtnDongYDN;
    EditText EdUserID, EdPass;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        nhanVienDAO = new NhanVienDAO(this);
        EdUserID = (EditText) findViewById(R.id.Edtxtuser);
        EdPass = (EditText) findViewById(R.id.EdtxPass);
        BtnDK = (Button) findViewById(R.id.BtnDK);
        BtnDongYDN = (Button) findViewById(R.id.BtnDongyDN);
        HienThiDongyVsDangKy();
        BtnDongYDN.setOnClickListener(this);
        BtnDK.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiDongyVsDangKy();
    }

    private void HienThiDongyVsDangKy(){
        boolean kiemtra = nhanVienDAO.KiemTraNhanVien();
        if (kiemtra){
            BtnDK.setVisibility(View.GONE);
            BtnDongYDN.setVisibility(View.VISIBLE);
        }else{
            BtnDK.setVisibility(View.VISIBLE);
            BtnDongYDN.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        int ID = v.getId();
        switch (ID){
            case R.id.BtnDK:
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);

                break;

            case R.id.BtnDongyDN:
                String tendangnhap, matkhau;
                tendangnhap = EdUserID.getText().toString();
                matkhau = EdPass.getText().toString();
                int MaNV = nhanVienDAO.KiemTraDangNhap(tendangnhap,matkhau);
                if (MaNV!=0) {
                    Toast.makeText(this, getResources().getString(R.string.DNTC), Toast.LENGTH_SHORT).show();
                    Intent iHomePage = new Intent(SignIn.this, Homepage.class);
                    iHomePage.putExtra("tendn", tendangnhap);
                    iHomePage.putExtra("manhanvien",MaNV);
                    startActivity(iHomePage);
                }else
                {
                    Toast.makeText(this, getResources().getString(R.string.DNTB), Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}
