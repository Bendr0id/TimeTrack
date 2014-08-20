package com.vidi.timetrack.navigation;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

@EBean
public class NavigationAdapter extends BaseAdapter
{
	@RootContext
	Context context;

	List<NavigationEntry> naviMenuEntries;

	@AfterInject
	void initAdapter()
	{
		naviMenuEntries = new ArrayList<NavigationEntry>();
		naviMenuEntries.add(new NavigationEntry("Home", 0));
		naviMenuEntries.add(new NavigationEntry("Locations", 0));
		naviMenuEntries.add(new NavigationEntry("Settings", 0));
		naviMenuEntries.add(new NavigationEntry("About", 0));
		naviMenuEntries.add(new NavigationEntry("Exit", 0));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		NavigationItem navigationMenuItem;
		
		if (convertView == null)
		{
			navigationMenuItem = NavigationItem_.build(context);
		}
		else
		{
			navigationMenuItem = (NavigationItem_) convertView;
		}

		navigationMenuItem.bind(getItem(position));

		return navigationMenuItem;
	}

	public int getCount()
	{
		return naviMenuEntries.size();
	}

	public NavigationEntry getItem(int position)
	{
		return naviMenuEntries.get(position);
	}

	public int getItemIndex(NavigationEntry navigationMenuEntry)
	{
		return naviMenuEntries.indexOf(navigationMenuEntry);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}
}
