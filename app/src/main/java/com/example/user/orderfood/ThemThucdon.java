package com.example.user.orderfood;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.orderfood.CustomAdapter.AdapterHienthiThucdon;
import com.example.user.orderfood.DAO.LoaiMonanDAO;
import com.example.user.orderfood.DAO.MonanDAO;
import com.example.user.orderfood.DTO.LoaiMonanDTO;
import com.example.user.orderfood.DTO.MonanDTO;

import java.util.List;

/**
 * Created by USER on 9/11/2016.
 */
public class ThemThucdon extends AppCompatActivity implements View.OnClickListener{
    ImageButton imbtnThemthucdon;
    Spinner spnloaimon;
    public static int REQUESTCODE_THEMLOAIMONAN = 112;
    public static int REQUESTCODE_CHONMONAN = 113;
    List<LoaiMonanDTO> loaiMonanDTOs;
    LoaiMonanDAO loaiMonanDAO;
    ImageView imHinhthucdon;
    Button btn_ttd_dongy, btn_ttd_thoat;
    EditText edtenmonan, edgiatien;
    MonanDTO monanDTO;
    MonanDAO monanDAO;
    String SDuongdanHA;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);
        imHinhthucdon = (ImageView) findViewById(R.id.im_ttd_chonmon);
        imbtnThemthucdon = (ImageButton) findViewById(R.id.imbtn_ttd_themmonan);
        spnloaimon = (Spinner) findViewById(R.id.sp_ttd_monan);
        imbtnThemthucdon.setOnClickListener(this);
        loaiMonanDAO = new LoaiMonanDAO(this);
        imHinhthucdon.setOnClickListener(this);
        btn_ttd_dongy = (Button) findViewById(R.id.btn_ttd_dongy);
        btn_ttd_thoat = (Button) findViewById(R.id.btn_ttd_thoat);
        btn_ttd_dongy.setOnClickListener(this);
        btn_ttd_thoat.setOnClickListener(this);

        edtenmonan = (EditText) findViewById(R.id.ed_ttd_tenmonan);
        edgiatien = (EditText) findViewById(R.id.ed_ttd_giatien);
        monanDAO = new MonanDAO(this);
        HienthiThucdon();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imbtn_ttd_themmonan:
                Intent ithemmonan = new Intent(ThemThucdon.this,ThemLoaiMonan.class);
                ithemmonan.putExtra("pathhinhanh",SDuongdanHA);
                startActivityForResult(ithemmonan,REQUESTCODE_THEMLOAIMONAN);
                break;
            case R.id.im_ttd_chonmon:
                Intent ichonmonan = new Intent();
                ichonmonan.setType("image/*");
                ichonmonan.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(ichonmonan,"Chọn Hình Thực Đơn"),REQUESTCODE_CHONMONAN);
                break;
            case R.id.btn_ttd_dongy:
                monanDTO = new MonanDTO();
                int vitri = spnloaimon.getSelectedItemPosition();
                int Maloaimon = loaiMonanDTOs.get(vitri).getMaloai();
                String Stenmonan = edtenmonan.getText().toString();
                int InGiatien = Integer.parseInt(edgiatien.getText().toString());
                monanDTO.setMaloai(Maloaimon);
                monanDTO.setGiatien(InGiatien);
                monanDTO.setTenmonan(Stenmonan);
                monanDTO.setHinhanh(SDuongdanHA);
                boolean kiemtra = monanDAO.ThemMonan(monanDTO);
                if(kiemtra){
                    Toast.makeText(this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"Thêm Thất Bại!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_ttd_thoat:
                finish();
                break;
        }
    }
    public void HienthiThucdon(){
        loaiMonanDTOs = loaiMonanDAO.LayDSMonan();
        AdapterHienthiThucdon adapterHienthiThucdon = new AdapterHienthiThucdon(this,R.layout.custom_layout_monan, loaiMonanDTOs);
        spnloaimon.setAdapter(adapterHienthiThucdon);
        adapterHienthiThucdon.notifyDataSetChanged();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTCODE_THEMLOAIMONAN){
            if(resultCode== Activity.RESULT_OK){
                Intent intent = data;
                boolean kiemtra = intent.getBooleanExtra("kqthemmonan",false);
                if(kiemtra){
                    HienthiThucdon();
                    Toast.makeText(this, "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this,"Thêm Thất Bại!",Toast.LENGTH_SHORT).show();
                }

            }
        }else if(requestCode==REQUESTCODE_CHONMONAN){
            if((resultCode== Activity.RESULT_OK)&&(data!=null)) {
                    SDuongdanHA = Uri.parse(data.getData().toString()).toString();
                    imHinhthucdon.setImageURI(data.getData());

            }

        }
    }



}
