package com.example.user.orderfood.FragmentApp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.user.orderfood.CustomAdapter.AdapterHienThiBanan;
import com.example.user.orderfood.CustomAdapter.AdapterHienthiDSMonan;
import com.example.user.orderfood.DAO.GoimonDAO;
import com.example.user.orderfood.DAO.MonanDAO;
import com.example.user.orderfood.DTO.MonanDTO;
import com.example.user.orderfood.R;
import com.example.user.orderfood.SoluongMonActivity;

import java.util.List;

/**
 * Created by USER on 10/4/2016.
 */
public class DanhsachMonanFragment extends android.support.v4.app.Fragment {
    List<MonanDTO> monanDTOs;
    MonanDAO monanDAO;
    AdapterHienthiDSMonan adapterHienthiDSMonan;
    GridView gridView;
    int maloai;
    int maban;
    String tinhtrangbanan;
    GoimonDAO goimonDAO;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthidsmonan, container, false);
        gridView = (GridView) view.findViewById(R.id.gvhtdsmonan);
        monanDAO = new MonanDAO(getActivity());
        goimonDAO = new GoimonDAO(getActivity());
        Bundle bundle = getArguments();
        if(bundle!=null){
            maloai = bundle.getInt("maloai");
            maban = bundle.getInt("maban");
            tinhtrangbanan = bundle.getString("tinhtrang");
            HienthiDSMonan(maloai);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int mamonan = monanDTOs.get(position).getMamonan();
                    if (maban!=0) {
                        Intent isoluongmonan = new Intent(getActivity(), SoluongMonActivity.class);
                        isoluongmonan.putExtra("mamonan", mamonan);
                        isoluongmonan.putExtra("maban", maban);
                        startActivity(isoluongmonan);
                        Log.d("mamonands",mamonan+"");
                    }
                }
            });
        }
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStackImmediate("loaimonan", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });
        return view;
    }
    public void HienthiDSMonan(int maloai){
        monanDTOs = monanDAO.LayDSMonan(maloai);
        adapterHienthiDSMonan =new AdapterHienthiDSMonan(getActivity(),R.layout.custom_layout_hienthidanhsachmonan,monanDTOs);
        gridView.setAdapter(adapterHienthiDSMonan);
        adapterHienthiDSMonan.notifyDataSetChanged();

    }

}
