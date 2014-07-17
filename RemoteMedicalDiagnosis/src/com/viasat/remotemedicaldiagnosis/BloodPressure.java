package com.viasat.remotemedicaldiagnosis;

import java.util.Random;

import com.viasat.installtools.E;
import com.viasat.installtools.R;
import com.viasat.installtools.dishinstall.ElevationActivity;
import com.viasat.installtools.dishinstall.MainActivityDishInstall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class BloodPressure extends NavigationDrawer
{
	
	int systolic = 0;
	int diastolic = 0;
	int pulse = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	
    	setContentView(R.layout.blood_pressure);
    	
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        
    	TextView systolicTextView = (TextView) findViewById(R.id.sys_text);
    	TextView diastolicTextView = (TextView) findViewById(R.id.sys_text);
    	TextView pulseTextView = (TextView) findViewById(R.id.pulse_text);
        
    	Random random = new Random();
    	
    	pulse = random.nextInt(50) + 50; //between 50-100
    	systolic = random.nextInt(100) + 100; //between 100-200
    	
    	if (systolic > 180)
    	{
    		diastolic = random.nextInt(10) + 110; //between 110-120
    		lp.addRule(RelativeLayout.ALIGN_LEFT, R.id.red_block);
    		lp.addRule(RelativeLayout.ABOVE, R.id.dark_orange_block);
    	}
    	else if (systolic > 160)
    	{
    		diastolic = random.nextInt(10) + 100; //between 100-110
    		lp.addRule(RelativeLayout.ALIGN_LEFT, R.id.dark_orange_block);
    		lp.addRule(RelativeLayout.ABOVE, R.id.light_orange_block);
    	}
    	else if (systolic > 140)
    	{
    		diastolic = random.nextInt(10) + 90;
    		lp.addRule(RelativeLayout.ALIGN_LEFT, R.id.light_orange_block);
    		lp.addRule(RelativeLayout.ABOVE, R.id.yellow_block);
    	}
    	else if (systolic > 120)
    	{
    		diastolic = random.nextInt(10) + 80; //between 80-90
    		lp.addRule(RelativeLayout.ALIGN_LEFT, R.id.yellow_block);
    		lp.addRule(RelativeLayout.ABOVE, R.id.green_block);
    	}
    	else
    	{
    		diastolic = random.nextInt(30) + 50; //between 50-80
    		lp.addRule(RelativeLayout.ALIGN_LEFT, R.id.green_block);
    	}
    	
    	//Align arrow to the correct place
    	ImageView arrowImage = (ImageView) findViewById(R.id.arrow);
    	arrowImage.setLayoutParams(lp);
    	
    	systolicTextView.setText(systolic);
    	diastolicTextView.setText(diastolic);
    	pulseTextView.setText(pulse);
    	
    	if (E.patient != null)
    	{
    		E.patient.setSystolic(systolic);
    		E.patient.setDiastolic(diastolic);
    		E.patient.setPulse(pulse);
    	}
    	
        Button nextButton = (Button) findViewById(R.id.send_button);
        nextButton.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	//must call separate method to pass context
            	
            	//TAKE THIS OUT
            	E.patient = new Patient("Kendall", "Wilson", "07/23/93", "F",
            			"Pains in stomach", "None", "No drugs");
            	//END TAKE THIS OUT
            	
            	sendInfo();
            }
        });
    	
    }
    
    private void sendInfo()
    {
    	if (E.patient != null)
    	{
    		E.patient.sendPatient(this);
    	}
    }
    
	
}
