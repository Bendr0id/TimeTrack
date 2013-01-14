package com.vidi.timetrack.activities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.vidi.timetrack.R;
import com.vidi.timetrack.fragments.LocationFragment;
import com.vidi.timetrack.fragments.RecordFragment;
import com.vidi.timetrack.fragments.SettingsFragment;

@ContentView(R.layout.activity_timetrack)
public class TimeTrackActivity extends RoboSherlockFragmentActivity implements ActionBar.TabListener
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TimeTrackActivity.class);

	@InjectResource(R.string.record_tab)
	private String recordTab;

	@InjectResource(R.string.location_tab)
	private String locationTab;

	@InjectResource(R.string.settings_tab)
	private String settingsTab;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		createActionBar();
	}

	private void createActionBar()
	{
		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		bar.addTab(createTab(bar.newTab(), recordTab));
		bar.addTab(createTab(bar.newTab(), locationTab));
		bar.addTab(createTab(bar.newTab(), settingsTab));
	}

	private ActionBar.Tab createTab(ActionBar.Tab tab, String text)
	{
		tab.setText(text);
		tab.setTabListener(this);

		return tab;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
		if (tab.getPosition() == 0)
		{
			ft.replace(android.R.id.content, new RecordFragment());
		}
		else if (tab.getPosition() == 1)
		{
			ft.replace(android.R.id.content, new LocationFragment());
		}
		else
		{
			ft.replace(android.R.id.content, new SettingsFragment());
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft)
	{
	}
}
