package com.vidi.timetrack.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.vidi.timetrack.R;
import com.vidi.timetrack.tabs.LocationFragment_;
import com.vidi.timetrack.tabs.RecordFragment_;
import com.vidi.timetrack.tabs.SettingsFragment_;
import com.vidi.timetrack.tabs.TabsAdapter;

@EActivity(R.layout.activity_maintab)
public class MainTabActivity extends SherlockFragmentActivity
{
	@StringRes(R.string.record_tab)
	String recordTab;

	@StringRes(R.string.location_tab)
	String locationTab;

	@StringRes(R.string.settings_tab)
	String settingsTab;

	@ViewById(R.id.pager)
	ViewPager pager;

	private TabsAdapter tabsAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@AfterViews
	public void loadView()
	{
		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabsAdapter = new TabsAdapter(this, pager);

		tabsAdapter.addTab(bar.newTab().setText(recordTab), RecordFragment_.class, null);
		tabsAdapter.addTab(bar.newTab().setText(settingsTab), SettingsFragment_.class, null);
		tabsAdapter.addTab(bar.newTab().setText(locationTab), LocationFragment_.class, null);
	}
}
