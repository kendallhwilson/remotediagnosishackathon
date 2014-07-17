package com.viasat.remotemedicaldiagnosis;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;

public class BloodPressure extends NavigationDrawer
{
	
	int systolic = 0;
	int diastolic = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
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
