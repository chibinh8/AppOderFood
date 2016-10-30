package com.example.user.orderfood.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.orderfood.DAO.BanAnDAO;
import com.example.user.orderfood.DAO.GoimonDAO;
import com.example.user.orderfood.DTO.BanAnDTO;
import com.example.user.orderfood.DTO.GoimonDTO;
import com.example.user.orderfood.FragmentApp.HienthiThucdonFragment;
import com.example.user.orderfood.Homepage;
import com.example.user.orderfood.R;
import com.example.user.orderfood.Thanhtoan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by USER on 9/9/2016.
 */
public class AdapterHienThiBanan extends BaseAdapter implements View.OnClickListener, OnBackStackChangedListener{
    Context context;
    int Layout;
    List<BanAnDTO> ListBanAnDTO;
    ViewHolderBanAn viewHolderBanAn;
    BanAnDAO banAnDAO;
    GoimonDTO goimonDTO;
    GoimonDAO goimonDAO;
    Fragment fragment;
    FragmentManager fragmentManager;
    public  AdapterHienThiBanan(Context context, int Layout, List<BanAnDTO> ListBanAnDTO){
        this.context = context;
        this.Layout = Layout;
        this.ListBanAnDTO = ListBanAnDTO;
        banAnDAO = new BanAnDAO(context);
        goimonDAO = new GoimonDAO(context);
        fragmentManager = ((Homepage)context).getSupportFragmentManager();
    }
    @Override
    public int getCount() {
        return this.ListBanAnDTO.size();
    }

    @Override
    public Object getItem(int position) {
        return ListBanAnDTO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ListBanAnDTO.get(position).getMaBan();
    }

    @Override
    public void onBackStackChanged() {

        BanAnDTO banAnDTO = ListBanAnDTO.get(viewHolderBanAn.Position);
        String tinhtrangban = banAnDAO.LayTinhTrangBanan(banAnDTO.getMaBan());
        if (tinhtrangban.equals("True"))
            viewHolderBanAn.imBanan.setImageResource(R.drawable.banantrue);
        else
            viewHolderBanAn.imBanan.setImageResource(R.drawable.banan);
    }


    public class ViewHolderBanAn{
        TextView tvTenbanan;
        ImageView imBanan, imGoimon, imThanhtoan, imAnButton;
        int Position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view==null){
            viewHolderBanAn = new ViewHolderBanAn();
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
            view = layoutInflater.inflate(R.layout.custom_layout_banan,parent, false);
            viewHolderBanAn.tvTenbanan = (TextView) view.findViewById(R.id.tvCusTenbanan);
            viewHolderBanAn.imBanan = (ImageView) view.findViewById(R.id.imBanan);
            viewHolderBanAn.imGoimon = (ImageView) view.findViewById(R.id.imgoimon);
            viewHolderBanAn.imThanhtoan = (ImageView) view.findViewById(R.id.imThanhtoan);
            viewHolderBanAn.imAnButton = (ImageView) view.findViewById(R.id.imAnbutton);
            viewHolderBanAn.Position = position;
            view.setTag(viewHolderBanAn);

        }
        else{

            viewHolderBanAn = (ViewHolderBanAn) view.getTag();

        }
        viewHolderBanAn.imBanan.setOnClickListener(this);
        viewHolderBanAn.imGoimon.setOnClickListener(this);
        viewHolderBanAn.imThanhtoan.setOnClickListener(this);
        BanAnDTO banAnDTO = ListBanAnDTO.get(position);
        String tinhtrangban = banAnDAO.LayTinhTrangBanan(banAnDTO.getMaBan());
        if (tinhtrangban.equals("True"))
            viewHolderBanAn.imBanan.setImageResource(R.drawable.banantrue);
        else
            viewHolderBanAn.imBanan.setImageResource(R.drawable.banan);

        viewHolderBanAn.tvTenbanan.setText(banAnDTO.getTenBan());
        HienThiNutChon(banAnDTO);
        return view;
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        viewHolderBanAn = (ViewHolderBanAn) ((View)v.getParent()).getTag();
        int vitri = viewHolderBanAn.Position;
        int maban = this.ListBanAnDTO.get(vitri).getMaBan();
        String tinhtrangBanan = banAnDAO.LayTinhTrangBanan(maban);
        switch (id){
            case R.id.imBanan:

                this.ListBanAnDTO.get(viewHolderBanAn.Position).setDuocchon(true);
                HienThiNutChon(this.ListBanAnDTO.get(viewHolderBanAn.Position));
                break;
            case R.id.imgoimon:
                Intent iManv = ((Homepage)context).getIntent();
                int MaNV = iManv.getIntExtra("manhanvien",0);
                Log.d("MaNV",MaNV+"");
                if(tinhtrangBanan.equals("False")||tinhtrangBanan.equals("")||tinhtrangBanan==null){
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yy");
                    String date = dateFormat.format(calendar.getTime());
                    goimonDTO = new GoimonDTO();
                    goimonDTO.setMaban(maban);
                    goimonDTO.setMaNV(MaNV);
                    goimonDTO.setNgaygoi(date);
                    goimonDTO.setTinhtrang("False");

                    if(goimonDAO.ThemGoimon(goimonDTO)==false)
                        Toast.makeText(this.context,"Thêm gọi món thất bại", Toast.LENGTH_SHORT).show();
                    if( !(banAnDAO.CapnhatTinhtrang(maban,"True")))
                        Toast.makeText(this.context,"Cập nhật tình trạng thất bại", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this.context,"Cập nhật tình trạng thành công", Toast.LENGTH_SHORT).show();
                }

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                HienthiThucdonFragment hienthiThucdonFragment = new HienthiThucdonFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("maban",maban);
                bundle.putCharSequence("tinhtrang",tinhtrangBanan);
                hienthiThucdonFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.content,hienthiThucdonFragment).addToBackStack("hienthibanan");
                fragmentTransaction.commit();

                break;
            case R.id.imThanhtoan:
                Intent ithanhtoan = new Intent(context, Thanhtoan.class);
                ithanhtoan.putExtra("maban",maban);
                context.startActivity(ithanhtoan);
                break;


        }

    }

    private void HienThiNutChon(BanAnDTO banAnDTO){
        if(banAnDTO.GetisDuocchon()){
            viewHolderBanAn.imThanhtoan.setVisibility(View.VISIBLE);
            viewHolderBanAn.imAnButton.setVisibility(View.VISIBLE);
            viewHolderBanAn.imGoimon.setVisibility(View.VISIBLE);
        }
        else{
            viewHolderBanAn.imThanhtoan.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imAnButton.setVisibility(View.INVISIBLE);
            viewHolderBanAn.imGoimon.setVisibility(View.INVISIBLE);
        }

    }


}
