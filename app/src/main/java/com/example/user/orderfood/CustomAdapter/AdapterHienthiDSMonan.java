package com.example.user.orderfood.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.orderfood.DAO.LoaiMonanDAO;
import com.example.user.orderfood.DAO.MonanDAO;
import com.example.user.orderfood.DTO.LoaiMonanDTO;
import com.example.user.orderfood.DTO.MonanDTO;
import com.example.user.orderfood.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by USER on 10/4/2016.
 */
public class AdapterHienthiDSMonan extends BaseAdapter{

    int Layout;
    List<MonanDTO> monanDTOs;
    Context context;
    MonanDAO monanDAO;

    public AdapterHienthiDSMonan(Context context, int layout, List<MonanDTO> monanDTOs){
        this.context = context;
        this.Layout = layout;
        this.monanDTOs = monanDTOs;
        monanDAO = new MonanDAO(context);
    }
    @Override
    public int getCount() {
        return monanDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        return monanDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return monanDTOs.get(position).getMaloai();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolderMonan viewHolderMonan;

        if(view==null){
            viewHolderMonan = new ViewHolderMonan();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout_hienthidanhsachmonan,parent,false);
            viewHolderMonan.tvTenMonan = (TextView) view.findViewById(R.id.tvcust_tenloai);
            viewHolderMonan.tvGiaMonan = (TextView) view.findViewById(R.id.tvcust_giamonan);
            viewHolderMonan.imHinhanh = (ImageView) view.findViewById(R.id.im_cust_monan);
            view.setTag(viewHolderMonan);

        }else{

            viewHolderMonan = (ViewHolderMonan) view.getTag();
        }

        viewHolderMonan.tvTenMonan.setText("Tên Món: "+ monanDTOs.get(position).getTenmonan());
        viewHolderMonan.tvGiaMonan.setText("Giá Tiền: "+ monanDTOs.get(position).getGiatien()+"");
        Uri uri=null;
        if(monanDTOs.get(position).getHinhanh()!=null&&(!monanDTOs.get(position).getHinhanh().equals(""))){
            uri = Uri.parse(monanDTOs.get(position).getHinhanh());
        }
        if(uri!=null)
            viewHolderMonan.imHinhanh.setImageURI(uri);

        return view;
    }

    public class ViewHolderMonan{
        TextView tvTenMonan;
        TextView tvGiaMonan;
        ImageView imHinhanh;
    }


}
