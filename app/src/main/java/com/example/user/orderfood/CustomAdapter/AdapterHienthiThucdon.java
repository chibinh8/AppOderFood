package com.example.user.orderfood.CustomAdapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.orderfood.DTO.LoaiMonanDTO;
import com.example.user.orderfood.R;

import java.util.List;

/**
 * Created by USER on 9/13/2016.
 */
public class AdapterHienthiThucdon extends BaseAdapter{
    Context context;
    int layout;
    List<LoaiMonanDTO> loaiMonanDTOs;
    public AdapterHienthiThucdon(Context context, int layout, List<LoaiMonanDTO> loaiMonanDTOs){
        this.layout = layout;
        this.context = context;
        this.loaiMonanDTOs = loaiMonanDTOs;
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
    public class ViewHolderLoaiMon{
        TextView sTenLoaiMon;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolderLoaiMon viewHolderLoaiMon;
        if(view==null){
            viewHolderLoaiMon  = new ViewHolderLoaiMon();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_layout_monan,parent,false);
            viewHolderLoaiMon.sTenLoaiMon = (TextView)view.findViewById(R.id.tvTenmonan);
            view.setTag(viewHolderLoaiMon);
        }else {
            viewHolderLoaiMon = (ViewHolderLoaiMon) view.getTag();
        }
        viewHolderLoaiMon.sTenLoaiMon.setText(loaiMonanDTOs.get(position).getTenloai());

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolderLoaiMon viewHolderLoaiMon;
        if(view==null){
            viewHolderLoaiMon  = new ViewHolderLoaiMon();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_layout_monan,parent,false);
            viewHolderLoaiMon.sTenLoaiMon = (TextView)view.findViewById(R.id.tvTenmonan);
            view.setTag(viewHolderLoaiMon);
        }else {
            viewHolderLoaiMon = (ViewHolderLoaiMon) view.getTag();
        }
        viewHolderLoaiMon.sTenLoaiMon.setText(loaiMonanDTOs.get(position).getTenloai());

        return view;
    }
}
