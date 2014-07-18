package com.viasat.remotemedicaldiagnosis;

import java.lang.StringBuilder;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

public class PatientInfo extends NavigationDrawer {
    String birthday;
    TextView name;
    TextView bday;
	RadioButton male;
	RadioButton female;
	TextView reason;
	TextView meds;
	TextView surgery;
	TextView allergies;
	String gender = " ";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_info);
        
        name = (TextView) findViewById(R.id.patient_name);
        bday = (TextView) findViewById(R.id.patient_birthday);
    	male = (RadioButton) findViewById(R.id.male);
    	female = (RadioButton) findViewById(R.id.female);
    	reason = (TextView) findViewById(R.id.PatientReason);
    	meds = (TextView) findViewById(R.id.PatientMedications);
    	surgery = (TextView) findViewById(R.id.PatientSurgery);
    	allergies = (TextView) findViewById(R.id.patient_allergies);
    	
    	Button sendButton = (Button) findViewById(R.id.sendButton);
		sendButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				sendInfo();
				//must use separate to send context
				
			}
		});
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
    
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked)
                	gender = "male";
                break;
            case R.id.female:
                if (checked)
                	gender = "female";
                break;
        }
    }
    
    private void sendInfo()
    {

		Patient patient = new Patient(name.getText().toString(), bday.getText().toString(), gender,
				reason.getText().toString(),meds.getText().toString(), 
				surgery.getText().toString(), allergies.getText().toString());
		patient.sendPatient(this);
    }
}