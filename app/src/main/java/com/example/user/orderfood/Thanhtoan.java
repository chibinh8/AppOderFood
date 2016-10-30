package com.example.user.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.orderfood.CustomAdapter.AdapterDSThanhtoan;
import com.example.user.orderfood.CustomAdapter.AdapterHienthiDSMonan;
import com.example.user.orderfood.CustomAdapter.AdapterHienthiLoaimonan;
import com.example.user.orderfood.DAO.BanAnDAO;
import com.example.user.orderfood.DAO.GoimonDAO;
import com.example.user.orderfood.DAO.ThanhtoanDAO;
import com.example.user.orderfood.DTO.ThanhtoanDTO;
import com.example.user.orderfood.FragmentApp.HienthibananFragment;

import java.util.List;

/**
 * Created by USER on 10/11/2016.
 */
public class Thanhtoan extends AppCompatActivity implements View.OnClickListener{
    List<ThanhtoanDTO> thanhtoanDTOs;
    ThanhtoanDAO thanhtoanDAO;
    GoimonDAO goimonDAO;
    int maban;
    GridView grvThanhtoan;
    Button BtnThanhtoan, BtnThoat;
    TextView tvTongtien;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    BanAnDAO banAnDAO;
    int magoimon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        grvThanhtoan = (GridView) findViewById(R.id.grvdsthanhtoan);
        BtnThanhtoan = (Button) findViewById(R.id.btnthanhtoan);
        BtnThoat = (Button) findViewById(R.id.btnthoat);
        tvTongtien = (TextView) findViewById(R.id.tvtongtien);
        BtnThanhtoan.setOnClickListener(this);
        BtnThoat.setOnClickListener(this);

        thanhtoanDAO = new ThanhtoanDAO(this);
        goimonDAO =new GoimonDAO(this);
        Intent iMaban = getIntent();
        maban = iMaban.getIntExtra("maban",0);
        HienthiDSThanhtoan();
        fragmentManager = getSupportFragmentManager();
        banAnDAO =new BanAnDAO(this);
    }
    public void HienthiDSThanhtoan(){
        magoimon = goimonDAO.LayMaGoimon(maban,"False");
        if(magoimon!=0) {
            thanhtoanDTOs = thanhtoanDAO.LayDsThanhtoan(magoimon);
            AdapterDSThanhtoan adapterDSThanhtoan = new AdapterDSThanhtoan(this, R.layout.custom_layout_thanhtoan, thanhtoanDTOs);
            grvThanhtoan.setAdapter(adapterDSThanhtoan);
            adapterDSThanhtoan.notifyDataSetChanged();
        }
        Log.d("Magoimon",magoimon+"");
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnthanhtoan:
               long tongtien =0;
                for(int i=0; i<thanhtoanDTOs.size();i++){
                    tongtien += thanhtoanDTOs.get(i).getSoluong()*thanhtoanDTOs.get(i).getGiatien();
                }
                tvTongtien.setText("Tổng tiền:       "+String.valueOf(tongtien));
                if( !(banAnDAO.CapnhatTinhtrang(maban,"False")))
                    Toast.makeText(this,"Cập nhật tình trạng thất bại", Toast.LENGTH_SHORT).show();

                goimonDAO.CapnhatTinhtrangGoimon(magoimon,"True");

                break;
            case R.id.btnthoat:
                fragmentManager.popBackStackImmediate("hienthibanan", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                finish();
                break;

        }

    }
}
