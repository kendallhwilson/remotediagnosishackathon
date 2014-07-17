package com.viasat.remotemedicaldiagnosis;

import java.util.Random;

import com.viasat.remotemedicaldiagnosis.R;








import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
    	final ViewTreeObserver vto = bar.getViewTreeObserver();
    	vto.addOnGlobalLayoutListener(this);

    }

	@Override
	public void onGlobalLayout() {
		// TODO Auto-generated method stub
		redBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		darkOrangeBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		lightOrangeBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		yellowBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		greenBlock.getLayoutParams().height = bar.getHeight()/5 - 19;
		
	}
    
  

	
	
}
