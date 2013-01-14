package com.vidi.timetrack.fragments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roboguice.inject.ContentView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.R;
import com.vidi.timetrack.db.entities.Record;

@ContentView(R.layout.fragment_settings)
public class SettingsFragment extends RoboSherlockFragment
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SettingsFragment.class);

	@Inject
	private Dao<Record, Integer> recordDao;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_settings, container, false);
	}
}
