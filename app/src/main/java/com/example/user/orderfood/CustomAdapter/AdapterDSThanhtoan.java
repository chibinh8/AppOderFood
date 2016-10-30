package com.example.user.orderfood.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.orderfood.DAO.MonanDAO;
import com.example.user.orderfood.DAO.ThanhtoanDAO;
import com.example.user.orderfood.DTO.BanAnDTO;
import com.example.user.orderfood.DTO.MonanDTO;
import com.example.user.orderfood.DTO.ThanhtoanDTO;
import com.example.user.orderfood.R;

import java.util.List;

/**
 * Created by USER on 10/11/2016.
 */
public class AdapterDSThanhtoan extends BaseAdapter {
    int Layout;
    List<ThanhtoanDTO> thanhtoanDTOs;
    Context context;
    ThanhtoanDAO thanhtoanDAO;
    ViewHolderDSThanhtoan viewHolderDSThanhtoan;
    public AdapterDSThanhtoan(Context context, int layout, List<ThanhtoanDTO> thanhtoanDTOs){
        this.context = context;
        this.Layout = layout;
        this.thanhtoanDTOs = thanhtoanDTOs;
        thanhtoanDAO = new ThanhtoanDAO(context);
    }

    @Override
    public int getCount() {
        return thanhtoanDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return thanhtoanDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return thanhtoanDTOs.get(position).getMamonan();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view==null){
            viewHolderDSThanhtoan = new ViewHolderDSThanhtoan();
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
            view = layoutInflater.inflate(R.layout.custom_layout_thanhtoan,parent, false);
            viewHolderDSThanhtoan.tvTenmonan = (TextView) view.findViewById(R.id.tv_tttenmonan);
            viewHolderDSThanhtoan.tvsoluong = (TextView) view.findViewById(R.id.tv_ttsoluong);
            viewHolderDSThanhtoan.tvgiatien = (TextView) view.findViewById(R.id.tv_ttgiatien);
            view.setTag(viewHolderDSThanhtoan);

        }
        else{

            viewHolderDSThanhtoan = (ViewHolderDSThanhtoan) view.getTag();

        }

        viewHolderDSThanhtoan.tvTenmonan.setText(thanhtoanDTOs.get(position).getTenmonan());
        viewHolderDSThanhtoan.tvsoluong.setText(String.valueOf(thanhtoanDTOs.get(position).getSoluong()));
        viewHolderDSThanhtoan.tvgiatien.setText(String.valueOf(thanhtoanDTOs.get(position).getGiatien()));

        return view;
    }

    public class ViewHolderDSThanhtoan{
        TextView tvTenmonan;
        TextView tvsoluong, tvgiatien;
    }
}
