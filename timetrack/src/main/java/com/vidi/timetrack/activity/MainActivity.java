package com.vidi.timetrack.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.ListView;

import com.vidi.timetrack.R;
import com.vidi.timetrack.fragment.HomeFragment_;
import com.vidi.timetrack.navigation.NavigationAdapter;
import com.vidi.timetrack.navigation.NavigationEntry;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EActivity(R.layout.ac_main)
@OptionsMenu(R.menu.main_context_menu)
public class MainActivity extends Activity
{
	private static final Logger LOGGER = LoggerFactory.getLogger(MainActivity.class);

	private ActionBar actionBar;

	@ViewById
	DrawerLayout drawerLayout;

	@ViewById
	ListView listMenu;

	@Bean
    NavigationAdapter navigationMenuAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		actionBar.setHomeAsUpIndicator(R.drawable.ic_navigation_drawer);
	}

	@AfterViews
	void loadView()
	{
		listMenu.setAdapter(navigationMenuAdapter);
		listMenu.setItemChecked(0, true);

		showFragment(0);
	}

	@ItemClick
	void listMenuItemClicked(NavigationEntry entry)
	{
		int selected = navigationMenuAdapter.getItemIndex(entry);

		showFragment(selected);

		drawerLayout.closeDrawer(Gravity.LEFT);
	}

	@OptionsItem(android.R.id.home)
	void home()
	{
		if (!drawerLayout.isDrawerOpen(Gravity.LEFT))
		{
			drawerLayout.openDrawer(Gravity.LEFT);
		}
		else
		{
			drawerLayout.closeDrawer(Gravity.LEFT);
		}
	}

	private void showFragment(int position)
	{
		Fragment fragment = null;
		switch (position)
		{
			case 0:
				fragment = new HomeFragment_();
				break;
			case 4:
				System.exit(0);
				break;
			default:
				break;
		}

		if (fragment != null)
		{
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(R.id.fragment_container, fragment);
			transaction.commit();
		}
	}
}
