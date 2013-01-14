package com.vidi.timetrack.fragments;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.R;
import com.vidi.timetrack.db.entities.Record;

@ContentView(R.layout.fragment_record)
public class RecordFragment extends RoboSherlockFragment implements OnClickListener
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RecordFragment.class);

	@Inject
	private Dao<Record, Integer> recordDao;

	@InjectView(R.id.recordListView)
	private ListView recordListView;

	@InjectView(R.id.addRecordButton)
	private Button addRecordButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_record, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		addRecordButton.setOnClickListener(this);
		recordListView.setAdapter(new ArrayAdapter<Record>(getActivity(), android.R.layout.simple_list_item_1, getAllRecord()));
	}

	private List<Record> getAllRecord()
	{
		List<Record> recordList = null;

		try
		{
			recordList = recordDao.queryForAll();

			for (Record record : recordList)
			{
				LOGGER.info("record: " + record);
			}
		}
		catch (SQLException e)
		{
			LOGGER.error("SQLException while fetching all records.", e);
		}

		return recordList;
	}

	private void createRecord()
	{
		Record record = new Record();
		record.setBegin(System.currentTimeMillis());

		try
		{

			recordDao.create(record);
			LOGGER.info("created new timeRecord {}.", record);
		}
		catch (SQLException e)
		{
			LOGGER.error("failed to insert time record {}.", record, e);
		}
	}

	@Override
	public void onClick(View v)
	{
		createRecord();
	}
}
