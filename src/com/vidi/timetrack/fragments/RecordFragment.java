package com.vidi.timetrack.fragments;

import java.sql.SQLException;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.R;
import com.vidi.timetrack.db.DatabaseHelper;
import com.vidi.timetrack.db.entities.Record;

@EFragment(R.layout.fragment_record)
public class RecordFragment extends SherlockFragment
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RecordFragment.class);

	@OrmLiteDao(helper = DatabaseHelper.class, model = Record.class)
	Dao<Record, Integer> recordDao;

	@ViewById(R.id.recordListView)
	ListView recordListView;

	@ViewById(R.id.addRecordButton)
	Button addRecordButton;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
	}

	@AfterViews
	void loadView()
	{
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

	@Click
	public void addRecordButton()
	{
		createRecord();
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
}
