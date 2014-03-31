package com.vidi.timetrack.wizard;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.StringRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragment;
import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.R;
import com.vidi.timetrack.db.DatabaseHelper;
import com.vidi.timetrack.db.entities.Location;

@EFragment(R.layout.fragment_wifi_wizard)
public class NFCFragment extends SherlockFragment
{
	private final static Logger LOGGER = LoggerFactory.getLogger(NFCFragment.class);

	@OrmLiteDao(helper = DatabaseHelper.class, model = Location.class)
	Dao<Location, Integer> locationDao;

	@StringRes
	String locationWizardTitle;

	@StringArrayRes
	String[] locationItems;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
}
