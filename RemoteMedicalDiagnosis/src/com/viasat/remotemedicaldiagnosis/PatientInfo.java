package com.viasat.remotemedicaldiagnosis;

import java.lang.StringBuilder;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class PatientInfo extends NavigationDrawer {
    String birthday;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_info);
    }
    
    public void startDatePicker(View view) {
        Calendar c = Calendar.getInstance();
        int startYear = c.get(Calendar.YEAR);
        int startMonth = c.get(Calendar.MONTH);
        int startDay = c.get(Calendar.DAY_OF_MONTH);
        
        DatePickerDialog dialog = new DatePickerDialog(PatientInfo.this, new DateSetListener(), startYear, startMonth, startDay);
        dialog.show();
    }
    
    class DateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            StringBuilder sb = new StringBuilder(100);
            birthday = sb.append(dayOfMonth).append('/').append(monthOfYear).append('/').append(dayOfMonth).toString();
        }
        
    }
}