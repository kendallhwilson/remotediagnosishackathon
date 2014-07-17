package com.viasat.remotemedicaldiagnosis;

import java.util.Random;
import com.viasat.remotemedicaldiagnosis.R;




import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
    	redBlock.setMinimumHeight(bar.getHeight()/5);
    	
    	Random random = new Random();
    	systolic = random.nextInt(100) + 100; //between 100-200
    	if (systolic > 180)
    	{
    		diastolic = random.nextInt(10) + 110; //between 110-120
    	}
    	else if (systolic > 160)
    	{
    		diastolic = random.nextInt(10) + 100; //between 100-110
    	}
    	else if (systolic > 140)
    	diastolic = random.nextInt(60) + 60;
    	
    	
    }
    
    
	
}
