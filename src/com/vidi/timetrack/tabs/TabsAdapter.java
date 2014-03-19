package com.vidi.timetrack.tabs;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class TabsAdapter extends FragmentPagerAdapter implements ActionBar.TabListener, ViewPager.OnPageChangeListener
{
	private Activity activity;
	private ActionBar actionBar;
	private ViewPager viewPager;

	private ArrayList<TabInfo> tabs = new ArrayList<TabInfo>();

	static final class TabInfo
	{
		private Class<?> clazz;
		private Bundle args;

		TabInfo(Class<?> clazz, Bundle args)
		{
			this.clazz = clazz;
			this.args = args;
		}
	}

	public TabsAdapter(SherlockFragmentActivity activity, ViewPager viewPager)
	{
		super(activity.getSupportFragmentManager());

		this.activity = activity;
		this.viewPager = viewPager;

		actionBar = activity.getSupportActionBar();

		viewPager.setAdapter(this);
		viewPager.setOnPageChangeListener(this);
	}

	public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args)
	{
		TabInfo info = new TabInfo(clss, args);
		tab.setTag(info);
		tab.setTabListener(this);

		tabs.add(info);
		actionBar.addTab(tab);

		notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return tabs.size();
	}

	@Override
	public Fragment getItem(int position)
	{
		TabInfo info = tabs.get(position);
		return Fragment.instantiate(activity, info.clazz.getName(), info.args);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
	}

	@Override
	public void onPageSelected(int position)
	{
		actionBar.setSelectedNavigationItem(position);
	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
		Object tag = tab.getTag();
		for (int i = 0; i < tabs.size(); i++)
		{
			if (tabs.get(i) == tag)
			{
				viewPager.setCurrentItem(i);
			}
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
