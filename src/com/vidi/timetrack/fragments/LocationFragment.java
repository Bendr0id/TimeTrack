package com.vidi.timetrack.fragments;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OrmLiteDao;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragment;
import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.R;
import com.vidi.timetrack.db.DatabaseHelper;
import com.vidi.timetrack.db.entities.Location;

@EFragment(R.layout.fragment_location)
@OptionsMenu(R.menu.fragment_location)
public class LocationFragment extends SherlockFragment
{
	@OrmLiteDao(helper = DatabaseHelper.class, model = Location.class)
	Dao<Location, Integer> locationDao;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
}
