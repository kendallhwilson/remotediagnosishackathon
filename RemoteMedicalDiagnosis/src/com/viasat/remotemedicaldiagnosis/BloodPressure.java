package com.viasat.remotemedicaldiagnosis;

import java.util.Random;
import com.viasat.remotemedicaldiagnosis.R;




import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
    	
    	RelativeLayout bar = (RelativeLayout) findViewById(R.id.wellness_bar);
    	ImageView redBlock = (ImageView) findViewById(R.id.red_block);
    	redBlock.getLayoutParams().height = bar.getHeight()/5;
    	
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        
        
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
    		diastolic = random.nextInt(10) + 90; //between 90-100
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
    		diastolic = random.nextInt(10) + 70; //between 70-80
    		lp.addRule(RelativeLayout.ALIGN_LEFT, R.id.green_block);
    	}
    	
    	TextView sysTextView = (TextView) findViewById(R.id.sys_text);
    	TextView diTextView = (TextView) findViewById(R.id.dia_text);
    	//TextView pulseTextView = (TextView) findViewById(R.id.heart_rate);
    	
    	if (E.patient != null)
    	{
    		E.patient.setDiastolic(diastolic);
    		E.patient.setSystolic(systolic);
    		//E.patient.setPulse(pulse);
    	}
    	
    	sysTextView.setText(systolic);
    	diTextView.setText(diastolic);
    	//pulseTextView.setText(pulse);
    	
    	//places arrow in correct place
    	//ImageView arrow = (ImageView) findViewById(R.id.arrow);
    	//arrow.setLayoutParams(lp);
    	
    	/*
    	Button sendButton = (Button) findViewById(R.id.sendButton);
		sendButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try 
				{
					//must use separate to send context
					sendInfo();
				} 
				catch (ActivityNotFoundException activityException) 
				{
				    Log.e("ViaSat", "Call failed", activityException);
				}
			}
		});
		*/
    	
    }
    
    private void sendInfo()
    {
    	if (E.patient != null)
    	{
    		E.patient.sendPatient(this);
    	}
    }
    
    
	
}
