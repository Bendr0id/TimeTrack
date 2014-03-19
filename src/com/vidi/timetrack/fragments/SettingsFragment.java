package com.vidi.timetrack.fragments;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OrmLiteDao;

import com.actionbarsherlock.app.SherlockFragment;
import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.R;
import com.vidi.timetrack.db.DatabaseHelper;
import com.vidi.timetrack.db.entities.Record;

@EFragment(R.layout.fragment_settings)
public class SettingsFragment extends SherlockFragment
{
	@OrmLiteDao(helper = DatabaseHelper.class, model = Record.class)
	Dao<Record, Integer> recordDao;
}
