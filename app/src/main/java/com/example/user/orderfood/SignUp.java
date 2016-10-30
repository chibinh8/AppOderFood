package com.example.user.orderfood;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.orderfood.DAO.NhanVienDAO;
import com.example.user.orderfood.DTO.NhanVienDTO;
import com.example.user.orderfood.Database.CreateDatabase;
import com.example.user.orderfood.FragmentApp.DatePickerFragment;

public class SignUp extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    Button BtnDongY, BtnThoat;
    EditText EdtxtMK, EdtxtNgaysinh, EdtxtCMND, EdtxtTenDN;
    RadioGroup RaGrSex;
    NhanVienDAO nhanVienDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        CreateDatabase createDatabase = new CreateDatabase(this);
        createDatabase.open();
        BtnDongY = (Button) findViewById(R.id.BtnDongy);
        BtnThoat = (Button) findViewById(R.id.BtnThoat);
        EdtxtMK = (EditText) findViewById(R.id.EtxtPassword);
        EdtxtNgaysinh = (EditText) findViewById(R.id.EtxtBirth);
        EdtxtCMND = (EditText) findViewById(R.id.EtxtCMND);
        RaGrSex = (RadioGroup) findViewById(R.id.RadGsex);
        EdtxtTenDN = (EditText) findViewById(R.id.EtxtID);
        BtnDongY.setOnClickListener(this);
        BtnThoat.setOnClickListener(this);
        EdtxtNgaysinh.setOnFocusChangeListener(this);
        nhanVienDAO = new NhanVienDAO(this);
    }


    @Override
    public void onClick(View v) {
        int Id = v.getId();
        switch (Id){
            case R.id.BtnDongy:
                String sTenDN = EdtxtTenDN.getText().toString();
                String sMatKhau = EdtxtMK.getText().toString();
                String sGioiTinh;
                switch (RaGrSex.getCheckedRadioButtonId()){
                    case R.id.RadBSexMale:
                        sGioiTinh = "Nam";
                        break;
                    case R.id.RadBSexFemale:
                        sGioiTinh = "Nữ";
                        break;
                    default:
                        sGioiTinh = "Nam";
                }
                String sNgaySinh = EdtxtNgaysinh.getText().toString();
                int iCMND = Integer.parseInt(EdtxtCMND.getText().toString());
                if((sTenDN == null)||(sTenDN.equals(""))){
                    Toast.makeText(SignUp.this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                }
                else if((sMatKhau == null)||(sMatKhau.equals(""))){
                    Toast.makeText(SignUp.this, "Vui lòng nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
                }
                else if((sNgaySinh == null)){
                    Toast.makeText(SignUp.this, "Vui lòng chọn Ngày Sinh", Toast.LENGTH_SHORT).show();
                }
                else{

                    NhanVienDTO nhanVienDTO = new NhanVienDTO();
                    nhanVienDTO.setTENDN(sTenDN);
                    nhanVienDTO.setMATKHAU(sMatKhau);
                    nhanVienDTO.setGIOITINH(sGioiTinh);
                    nhanVienDTO.setCMND(iCMND);
                    nhanVienDTO.setNGAYSINH(sNgaySinh);
                    long IsThemSQLSuccess  = nhanVienDAO.ThemNhanVien(nhanVienDTO);
                    if(IsThemSQLSuccess!=0){
                        Toast.makeText(this,getResources().getString(R.string.NoErrorSQL),Toast.LENGTH_SHORT).show();                    }
                    else{
                        Toast.makeText(this,getResources().getString(R.string.ErrorSQL),Toast.LENGTH_SHORT).show();
                    }
                }


                break;
            case R.id.BtnThoat:
                finish();
                break;

        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int Id = v.getId();
        switch (Id){
            case R.id.EtxtBirth:
                //Popup ngay sinh
                if(hasFocus) {
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getFragmentManager(),"NgaySinh");
                }

                break;

                }
    }
}
