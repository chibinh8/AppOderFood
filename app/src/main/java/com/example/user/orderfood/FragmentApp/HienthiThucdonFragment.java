package com.example.user.orderfood.FragmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.user.orderfood.CustomAdapter.AdapterHienthiLoaimonan;
import com.example.user.orderfood.CustomAdapter.AdapterHienthiThucdon;
import com.example.user.orderfood.DAO.LoaiMonanDAO;
import com.example.user.orderfood.DTO.LoaiMonanDTO;
import com.example.user.orderfood.Homepage;
import com.example.user.orderfood.R;
import com.example.user.orderfood.ThemThucdon;

import java.util.List;

/**
 * Created by USER on 9/11/2016.
 */
public class HienthiThucdonFragment extends Fragment{
    LoaiMonanDAO loaiMonanDAO;
    List<LoaiMonanDTO> loaiMonanDTOs;
    GridView gvloaimonan;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int Maban;
    String tinhtrangbanan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon,container,false);
        setHasOptionsMenu(true);
        ((Homepage)getActivity()).getSupportActionBar().setTitle(R.string.ThemTD);
        loaiMonanDAO = new LoaiMonanDAO(getActivity());
        gvloaimonan = (GridView) view.findViewById(R.id.gvhtthucdon);
        fragmentManager = getActivity().getSupportFragmentManager();
        Log.d("Binh","Create view");
        HienthiLoaiMonan();
        Bundle bmaban = getArguments();
        if (bmaban!=null){
            Maban = bmaban.getInt("maban");
            tinhtrangbanan = bmaban.getString("tinhtrang");
        }

        gvloaimonan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int imaloai  = loaiMonanDTOs.get(position).getMaloai();
                Bundle bundle = new Bundle();
                bundle.putInt("maloai",imaloai);
                bundle.putInt("maban",Maban);
                bundle.putCharSequence("tinhtrang",tinhtrangbanan);
                fragmentTransaction = fragmentManager.beginTransaction();
                DanhsachMonanFragment danhsachMonanFragment = new DanhsachMonanFragment();
                danhsachMonanFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.content,danhsachMonanFragment).addToBackStack("loaimonan");
                fragmentTransaction.commit();
            }

            });
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStackImmediate("hienthibanan", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });
        return view;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.add(1, R.id.itthemthucdon,1, R.string.ThemTD);
        menuItem.setIcon(R.drawable.logodangnhap);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        Toast.makeText(getActivity(),"Vui lòng thêm thực đơn!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itthemthucdon:
                Intent intent = new Intent(getActivity(), ThemThucdon.class);
                startActivity(intent);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void HienthiLoaiMonan(){
        loaiMonanDTOs =  loaiMonanDAO.LayDSMonan();
        AdapterHienthiLoaimonan adapterHienthiLoaimonan = new AdapterHienthiLoaimonan(getActivity(),R.layout.custom_layout_hienthiloaimonan, loaiMonanDTOs);
        gvloaimonan.setAdapter(adapterHienthiLoaimonan);
        adapterHienthiLoaimonan.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onStart();
        Log.d("Binh","On resume");
        HienthiLoaiMonan();
    }

}
