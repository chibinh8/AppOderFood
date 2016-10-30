package com.example.user.orderfood.CustomAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.orderfood.DAO.LoaiMonanDAO;
import com.example.user.orderfood.DTO.LoaiMonanDTO;
import com.example.user.orderfood.R;

import java.io.File;
import java.util.List;

/**
 * Created by USER on 9/25/2016.
 */
public class AdapterHienthiLoaimonan extends BaseAdapter implements View.OnClickListener{
    int Layout;
    List<LoaiMonanDTO> loaiMonanDTOs;
    Context context;
    LoaiMonanDAO loaiMonanDAO;

    public AdapterHienthiLoaimonan(Context context, int layout, List<LoaiMonanDTO> loaiMonanDTOs){
        this.context = context;
        this.Layout = layout;
        this.loaiMonanDTOs = loaiMonanDTOs;
        loaiMonanDAO = new LoaiMonanDAO(context);
    }
    @Override
    public int getCount() {
        return loaiMonanDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiMonanDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return loaiMonanDTOs.get(position).getMaloai();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolderLoaiMonan{
         TextView tvTenLoaiMon;
         ImageView imHinhanh;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolderLoaiMonan viewHolderLoaiMonan;
        if(view==null){
            viewHolderLoaiMonan = new ViewHolderLoaiMonan();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.custom_layout_hienthiloaimonan,parent,false);
            viewHolderLoaiMonan.imHinhanh = (ImageView) view.findViewById(R.id.im_cust_loaimonan);
            viewHolderLoaiMonan.tvTenLoaiMon = (TextView) view.findViewById(R.id.tvcust_tenloai);

            view.setTag(viewHolderLoaiMonan);
        }else{

            viewHolderLoaiMonan = (ViewHolderLoaiMonan) view.getTag();
        }
        String SPathHinhanh =  loaiMonanDAO.LayHinhAnh(loaiMonanDTOs.get(position).getMaloai());
        Uri uri = Uri.parse(SPathHinhanh);
        viewHolderLoaiMonan.imHinhanh.setImageURI(uri);
        viewHolderLoaiMonan.tvTenLoaiMon.setText(loaiMonanDTOs.get(position).getTenloai());
        return view;
    }
}
