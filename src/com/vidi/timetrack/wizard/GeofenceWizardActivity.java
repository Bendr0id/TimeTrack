package com.vidi.timetrack.wizard;

import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.vidi.timetrack.R;
import com.vidi.timetrack.wizard.GeofenceFragment_;

@EActivity(R.layout.activity_geofence_wizard)
public class GeofenceWizardActivity extends Activity
{
	private int currentStep;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		showFragment(0);
	}

	public void showFragment(int step)
	{
		Fragment fragment = new GeofenceFragment_();

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.fragmentContainer, fragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commitAllowingStateLoss();

		currentStep = step;
	}
}
