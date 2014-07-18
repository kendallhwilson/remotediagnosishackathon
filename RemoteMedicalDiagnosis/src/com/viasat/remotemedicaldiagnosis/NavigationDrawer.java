package com.viasat.remotemedicaldiagnosis;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;



public class NavigationDrawer extends Activity
{
 	protected DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
 	protected DrawerLayout fullLayout;
 	protected FrameLayout actContent;
 	protected ArrayList<View> mViews;

	class InternalAdapter extends BaseAdapter implements
	AdapterView.OnItemClickListener
	{
		
		//
		InternalAdapter()
		{
			mViews= new ArrayList<View>(3);
			LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

			mViews.add(layoutInflater.inflate(R.layout.nav_drawer_home, null));
			mViews.add(layoutInflater.inflate(R.layout.nav_drawer_patient_info, null));
			mViews.add(layoutInflater.inflate(R.layout.nav_drawer_blood_pressure, null));
			mViews.add(layoutInflater.inflate(R.layout.nav_drawer_share_image, null));
			mViews.add(layoutInflater.inflate(R.layout.nav_drawer_cam, null));
			mViews.add(layoutInflater.inflate(R.layout.nav_drawer_microscope, null));
			mViews.add(layoutInflater.inflate(R.layout.nav_drawer_help, null));
	
		}
		@Override
		public int getCount()
		{
			return mViews.size();
		}
		@Override
		public Object getItem(int position)
		{
			return mViews.get(position);
		}
		@Override
		public long getItemId(int position)
		{
			return mViews.get(position).getId();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			return (View) getItem(position);
		}
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			int vId= view.getId();
			switch (vId)
			{
			
				case R.id.nav_drawer_home:
					startActivity(new Intent(NavigationDrawer.this, MainActivity.class));
					break;
				case R.id.nav_drawer_cam:
					Intent camIntent = getPackageManager().getLaunchIntentForPackage("com.pas.webcam");
					startActivity(camIntent);
					break;
				case R.id.nav_drawer_microscope:
					Intent micIntent = getPackageManager().getLaunchIntentForPackage("com.yuvalluzon.yourmagnifier");
					startActivity(micIntent);
					break;
				case R.id.nav_drawer_blood_pressure:
					startActivity(new Intent(NavigationDrawer.this, BloodPressure.class));
					break;
				case R.id.nav_drawer_patient_info:
					startActivity(new Intent(NavigationDrawer.this, Patient.class));
				case R.id.nav_drawer_help:
					startActivity(new Intent(NavigationDrawer.this, Help.class));
				case R.id.nav_drawer_share_image:
					startActivity(new Intent(NavigationDrawer.this, SendImage.class));
					break;
//				case R.id.nav_drawer_video_conference:
//					startActivity(new Intent(NavigationDrawer.this, VideoConference.class));
//				case R.id.nav_drawer_microscope:
//					startActivity(new Intent(NavigationDrawer.this, Microscope.class));
			}
		}
	}
    @Override
    public void setContentView(final int layoutResID) 
    {
    	
        fullLayout= (DrawerLayout) getLayoutInflater().inflate(R.layout.main, null); // Your base layout here
        actContent= (FrameLayout) fullLayout.findViewById(R.id.content_frame);
        getLayoutInflater().inflate(layoutResID, actContent, true); // Setting the content of layout your provided to the act_content frame
        super.setContentView(fullLayout);
        
        // here you can get your drawer buttons and define how they should behave and what must they do, so you won't be needing to repeat it in every activity class
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList= (ListView) findViewById(R.id.left_drawer);
		// prepare drawer
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
		InternalAdapter adapter= new InternalAdapter();
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(adapter);
		//
		android.app.ActionBar actionBar= getActionBar();
		actionBar.setLogo(R.drawable.action_bar_home);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1E75CC")));

		//
		mDrawerToggle= new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.menu_navdrawer_icon, R.string.menu_open,
				R.string.menu_close);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		
		
    }
		
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);  
	    }
  
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) 
	    {
	        
	        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }   
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}