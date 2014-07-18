package com.viasat.remotemedicaldiagnosis;

import java.util.Random;

import com.viasat.remotemedicaldiagnosis.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    	
    	TextView normal = (TextView) findViewById(R.id.normal_text);
    	TextView prehypertension = (TextView) findViewById(R.id.prehypertension_text);
    	TextView high_stage1 = (TextView) findViewById(R.id.high_stage1_text);
    	TextView high_stage2 = (TextView) findViewById(R.id.high_stage2_text);
    	TextView hypertensive_crisis = (TextView) findViewById(R.id.hypertensive_crisis_text);
        
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
    	
    	TextView sysTextView = (TextView) findViewById(R.id.sys_num);
    	TextView diTextView = (TextView) findViewById(R.id.dia_num);
    	//TextView pulseTextView = (TextView) findViewById(R.id.heart_rate);
    	
    	if (E.patient != null)
    	{
    		E.patient.setDiastolic(diastolic);
    		E.patient.setSystolic(systolic);
    		//E.patient.setPulse(pulse);
    	}
    	
    	sysTextView.setText(Integer.toString(systolic));
    	diTextView.setText(Integer.toString(diastolic));
    	//pulseTextView.setText(pulse);
    	
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
    	diastolic = random.nextInt(60) + 60;
    	final ViewTreeObserver vto = bar.getViewTreeObserver();
    	vto.addOnGlobalLayoutListener(this);
    	
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
        
		int totalHeight = redBlock.getHeight() + darkOrangeBlock.getHeight() + lightOrangeBlock.getHeight()
		 + yellowBlock.getHeight() + greenBlock.getHeight();
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
