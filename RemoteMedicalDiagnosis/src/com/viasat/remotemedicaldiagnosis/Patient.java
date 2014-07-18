package com.viasat.remotemedicaldiagnosis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

public class Patient 
{
	private String firstName;
	private String lastName;
	private String birthday;
	private String gender;
	private String reason;
	private String surgery;
	private String drugs;
	private String allergies;
	
	//Added separately, not passed to constructor
	private int systolic;
	private int diastolic;
	private int pulse;
	
	public Patient(String mFirstName, String mLastName, String mBirthday, String mGender,
			String mReason, String mSurgery, String mDrugs, String mAllergies)
	{
		firstName = mFirstName;
		lastName = mLastName;
		birthday = mBirthday;
		gender = mGender;
		reason = mReason;
		surgery = mSurgery;
		drugs = mDrugs;
		allergies = mAllergies;
		
		systolic = 0;
		diastolic = 0;
		pulse = 0;
	}
	
	public void setSystolic(int mSystolic)
	{
		systolic = mSystolic;
	}

	public void setDiastolic(int mDiastolic)
	{
		diastolic = mDiastolic;
	}
	
	public void setPulse(int mPulse)
	{
		pulse = mPulse;
	}
	
	public void sendPatient(Context context)
	{
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		String subject = "PATIENT: " + firstName + " " + lastName + " INFORMATION";
		
		//If they have this info
		if (systolic != 0 && diastolic != 0 && pulse != 0)
		{
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Patient Name: " + firstName + " " + lastName
				+ "\nBirthday: " + birthday
				+ "\nGender: " + gender
				+ "\nReason for visit: " + reason
				+ "\nPast surgeries: " + surgery
				+ "\nDrugs taking: " + drugs
				+ "\nAllergies: " + allergies
				+ "\nMeasured heart rate: " + pulse
				+ "\nBlood pressure: " + systolic + "/" + diastolic);
		}
		else
		{
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Patient Name: " + firstName + " " + lastName
				+ "\nBirthday: " + birthday
				+ "\nGender: " + gender
				+ "\nReason for visit: " + reason
				+ "\nPast surgeries: " + surgery
				+ "\nDrugs taking: " + drugs);
		}
		sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
		sendIntent.setType("text/plain");
		context.startActivity(sendIntent);
	}
	
	public void sendImage(Context context)
	{
		/*
		InputStream in = null;
		OutputStream out = null;
		try
		{
			in = context.getResources().openRawResource(R.raw.ic_launcher);
			out = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "xray.jpg"));
			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}



		Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        String subject = "PATIENT: " + firstName + " " + lastName + " IMAGE";
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "File attached");
        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "xray.jpg"));
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(emailIntent);
        
        */
		
		Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");


        //Uri imageUri = Uri.parse("android.resource://com.viasat.remotemedicaldiagnosis/drawable/xray2");
        //Uri imageUri = Uri.parse("android.resource://com.viasat.remotemedicaldiagnosis/" +R.drawable/xray2.jpg);
        
        Uri imageUri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.xray2), null, null));
        
        share.putExtra(Intent.EXTRA_STREAM,imageUri);
        context.startActivity(Intent.createChooser(share, "Share Image"));
	}
	
	private void copyFile(InputStream in, OutputStream out) throws IOException
	{
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
	}

}
