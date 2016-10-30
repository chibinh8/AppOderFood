package com.example.user.orderfood.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.user.orderfood.CustomAdapter.AdapterHienThiBanan;
import com.example.user.orderfood.DAO.BanAnDAO;
import com.example.user.orderfood.DTO.BanAnDTO;
import com.example.user.orderfood.Homepage;
import com.example.user.orderfood.R;
import com.example.user.orderfood.ThemBanAn;

import java.util.List;

/**
 * Created by USER on 9/5/2016.
 */
public class HienthibananFragment extends Fragment{
    public static int REQUEST_CODE_THEM = 111;
    GridView gridView;
    List<BanAnDTO> ListBananDTO;
    BanAnDAO banAnDAO;
    AdapterHienThiBanan adapterHienThiBanan;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthibanan, container, false);
        setHasOptionsMenu(true);
        gridView = (GridView) view.findViewById(R.id.gvnbanan);
        banAnDAO = new BanAnDAO(getActivity());
        HienThiBanAn();
        ((Homepage)getActivity()).getSupportActionBar().setTitle(R.string.ThemBA);
        return view;
    }
    public void HienThiBanAn(){

        ListBananDTO = banAnDAO.LayDanhsachBA();
        adapterHienThiBanan =new AdapterHienThiBanan(getActivity(),R.layout.custom_layout_banan,ListBananDTO);
        gridView.setAdapter(adapterHienThiBanan);
        adapterHienThiBanan.notifyDataSetChanged();

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.add(1, R.id.itthembanan,1, R.string.ThemBA);
        menuItem.setIcon(R.drawable.thembanan);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        Toast.makeText(getActivity(),"Vui lòng thêm bàn ăn!",Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itthembanan:
                Intent intent = new Intent(getActivity(), ThemBanAn.class);
                startActivityForResult(intent,REQUEST_CODE_THEM);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_THEM){
            if(resultCode== Activity.RESULT_OK){
                boolean kiemtra = data.getBooleanExtra("ketquathembanan",false);
                if(kiemtra) {
                    HienThiBanAn();
                    Toast.makeText(getActivity(), "Thêm Thành Công!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(),"Thêm Thất Bại!",Toast.LENGTH_SHORT).show();
            }

        }

    }
}
