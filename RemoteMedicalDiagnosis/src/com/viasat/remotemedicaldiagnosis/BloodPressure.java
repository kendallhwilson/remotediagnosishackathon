package com.viasat.remotemedicaldiagnosis;

import java.util.Random;
import com.viasat.remotemedicaldiagnosis.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class BloodPressure extends NavigationDrawer implements OnGlobalLayoutListener
{
	
	int systolic = 0;
	int diastolic = 0;
	int pulse = 0;
	RelativeLayout bar;
	ImageView redBlock;
	ImageView darkOrangeBlock;
	ImageView lightOrangeBlock;
	ImageView yellowBlock;
	ImageView greenBlock;
	TextView normal;
	TextView prehypertension;
	TextView high_stage1;
	TextView high_stage2;
	TextView hypertensive_crisis;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.blood_pressure);
    	
    	bar = (RelativeLayout) findViewById(R.id.wellness_bar);
    	redBlock = (ImageView) findViewById(R.id.red_block);
    	darkOrangeBlock = (ImageView) findViewById(R.id.dark_orange_block);
    	lightOrangeBlock = (ImageView) findViewById(R.id.light_orange_block);
    	yellowBlock = (ImageView) findViewById(R.id.yellow_block);
    	greenBlock = (ImageView) findViewById(R.id.green_block);
    	
    	normal = (TextView) findViewById(R.id.normal_text);
    	prehypertension = (TextView) findViewById(R.id.prehypertension_text);
    	high_stage1 = (TextView) findViewById(R.id.high_stage1_text);
    	high_stage2 = (TextView) findViewById(R.id.high_stage2_text);
    	hypertensive_crisis = (TextView) findViewById(R.id.hypertensive_crisis_text);
        
    	Random random = new Random();
    	pulse = random.nextInt(50) + 50; //between 50-100
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

    	{
    		diastolic = random.nextInt(10) + 90; //between 90-100
    	}
    	else if (systolic > 120)
    	{
    		diastolic = random.nextInt(10) + 80; //between 80-90
    	}
    	else
    	{
    		diastolic = random.nextInt(10) + 70; //between 70-80
    	}
    	
    	if (E.patient != null)
    	{
    		E.patient.setDiastolic(diastolic);
    		E.patient.setSystolic(systolic);
    		E.patient.setPulse(pulse);
    	}
    	
    	Button sendButton = (Button) findViewById(R.id.sendButton);
		sendButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//must use separate to send context
				sendInfo();
			}
		});
	
    	final ViewTreeObserver vto = bar.getViewTreeObserver();
    	vto.addOnGlobalLayoutListener(this);
    	
		 //Timer
        CountDownTimer timer = new CountDownTimer(3000, 1000) 
        {
            ProgressBar mProgress = (ProgressBar) findViewById(R.id.progress_bar);
            RelativeLayout dimLayout = (RelativeLayout) findViewById( R.id.bac_dim_layout);
        	
            public void onTick(long millisUntilFinished) 
            {
            	dimLayout.setVisibility(View.VISIBLE);
            	mProgress.setVisibility(ProgressBar.VISIBLE);
            }
            public void onFinish()
            {
            	dimLayout.setVisibility(View.GONE);
            	mProgress.setVisibility(ProgressBar.GONE);
            	TextView sysTextView = (TextView) findViewById(R.id.sys_num);
            	TextView diTextView = (TextView) findViewById(R.id.dia_num);
            	TextView pulseTextView = (TextView) findViewById(R.id.pulse_num);
            	sysTextView.setText(Integer.toString(systolic));
            	diTextView.setText(Integer.toString(diastolic));
            	pulseTextView.setText(Integer.toString(pulse));
            	if (systolic > 180)
            	{
            		hypertensive_crisis.setVisibility(View.VISIBLE);
            	}
            	else if (systolic > 160)
            	{
            		high_stage2.setVisibility(View.VISIBLE);
            	}
            	else if (systolic > 140)

            	{
            		high_stage1.setVisibility(View.VISIBLE);
            	}
            	else if (systolic > 120)
            	{
            		prehypertension.setVisibility(View.VISIBLE);
            	}
            	else
            	{
            		normal.setVisibility(View.VISIBLE);
            	}
            }
        };
        
        timer.start();
    	
    }

	@Override
	public void onGlobalLayout() 
	{
		redBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		darkOrangeBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		lightOrangeBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		yellowBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		greenBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        
		ImageView arrow = (ImageView) findViewById(R.id.arrow);
		int heightArrow = (int) ((float) (systolic-100)*bar.getHeight()/100);
		
		LayoutParams currLP = (LayoutParams) arrow.getLayoutParams();
		currLP.topMargin = bar.getHeight() -heightArrow - redBlock.getHeight()/3;		
	}
    

    private void sendInfo()
    {
    	if (E.patient != null)
    	{
    		E.patient.sendPatient(this);
    	}
    }
    
    

	
}
