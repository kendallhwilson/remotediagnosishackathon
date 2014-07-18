package com.viasat.remotemedicaldiagnosis;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Patient 
{
	private String firstName;
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
	
	public Patient(String mFirstName, String mBirthday, String mGender,
			String mReason, String mSurgery, String mDrugs, String mAllergies)
	{
		firstName = mFirstName;
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
		String subject = "PATIENT: " + firstName  + " INFORMATION";
		
		//If they have this info
		if (systolic != 0 && diastolic != 0 && pulse != 0)
		{
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Patient Name: " + firstName
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
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Patient Name: " + firstName 
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
		
		Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/png");

        Uri imageUri = Uri.parse("android.resource://com.viasat.remotemedicaldiagnosis/drawable/xray2");
        
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        String subject = "Patient Name: " + firstName ;
        share.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        context.startActivity(Intent.createChooser(share, "Share Image"));
	}

}
