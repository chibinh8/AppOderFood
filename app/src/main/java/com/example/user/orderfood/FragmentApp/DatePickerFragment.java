package com.example.user.orderfood.FragmentApp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.user.orderfood.R;

import java.util.Calendar;

/**
 * Created by USER on 8/28/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();

        int iNam = calendar.get(Calendar.YEAR);
        int iThang = calendar.get(Calendar.MONTH);
        int iNgay = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this,iNgay, iThang, iNam);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        EditText EdNgSinh = (EditText) getActivity().findViewById(R.id.EtxtBirth);
        String sNgSinh = dayOfMonth + "/" + monthOfYear + "/" + year;
        EdNgSinh.setText(sNgSinh);
    }
}
