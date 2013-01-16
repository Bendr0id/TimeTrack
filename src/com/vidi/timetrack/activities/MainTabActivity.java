package com.vidi.timetrack.activities;

import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.vidi.timetrack.R;
import com.vidi.timetrack.adapter.TabsAdapter;
import com.vidi.timetrack.fragments.LocationFragment;
import com.vidi.timetrack.fragments.RecordFragment;
import com.vidi.timetrack.fragments.SettingsFragment;

@ContentView(R.layout.activity_maintab)
public class MainTabActivity extends RoboSherlockFragmentActivity
{
	@InjectResource(R.string.record_tab)
	private String recordTab;

	@InjectResource(R.string.location_tab)
	private String locationTab;

	@InjectResource(R.string.settings_tab)
	private String settingsTab;

	@InjectView(R.id.pager)
	private ViewPager pager;

	private TabsAdapter tabsAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabsAdapter = new TabsAdapter(this, pager);

		tabsAdapter.addTab(bar.newTab().setText(recordTab), RecordFragment.class, null);
		tabsAdapter.addTab(bar.newTab().setText(settingsTab), SettingsFragment.class, null);
		tabsAdapter.addTab(bar.newTab().setText(locationTab), LocationFragment.class, null);
	}
}
