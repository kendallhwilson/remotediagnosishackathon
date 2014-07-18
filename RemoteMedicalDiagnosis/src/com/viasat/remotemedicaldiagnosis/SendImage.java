package com.viasat.remotemedicaldiagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SendImage extends NavigationDrawer
{
	
	private boolean photoUploaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.send_image);
    	
    	final ImageView xray = (ImageView) findViewById(R.id.xrayView);
    	final Button uploadButton = (Button) findViewById(R.id.send_button);
    	final TextView titleView = (TextView) findViewById(R.id.image_titleView);
    	final TextView instructionView = (TextView) findViewById(R.id.image_instructionView);
		uploadButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				photoUploaded = !photoUploaded;
				uploadButton.setText(R.string.send_button_text);
				xray.setVisibility(View.VISIBLE);
				titleView.setVisibility(View.GONE);
				instructionView.setVisibility(View.GONE);
				if (photoUploaded)
				{
					E.patient = new Patient("K", "W", "bday", "gender", "Reason", "surgery", "Drugs", "allergies");
					sendImage();
				}
			}
		});
    }
    
    private void sendImage()
    {
		if (E.patient != null)
		{
			E.patient.sendImage(this);
		}
    }
}
