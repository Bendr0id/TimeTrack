package com.vidi.timetrack.activities;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.R;
import com.vidi.timetrack.db.entities.TimeRecord;

@ContentView(R.layout.activity_timetrack)
public class TimeTrackActivity extends RoboActivity implements OnClickListener
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TimeTrackActivity.class);

	@InjectView(R.id.scan_button)
	private Button scanButton;

	@InjectView(R.id.scan_result_listview)
	private ListView scanResultListView;

	@Inject
	private Dao<TimeRecord, Integer> timeRecordDao;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		scanButton.setOnClickListener(this);

		try
		{
			List<TimeRecord> timeRecordList = timeRecordDao.queryForAll();
			for (TimeRecord timeRecord : timeRecordList)
			{
				LOGGER.info("record: " + timeRecord);
			}
		}
		catch (SQLException e)
		{
			LOGGER.error("SQLException while fetching all records.", e);
		}
	}

	@Override
	public void onClick(View v)
	{
		if (v == scanButton)
		{
			LOGGER.info("scan button was clicked.");

			TimeRecord timeRecord = new TimeRecord();
			timeRecord.setBegin(System.currentTimeMillis());

			try
			{
				timeRecordDao.create(timeRecord);
				LOGGER.info("created new timeRecord {}.", timeRecord);
			}
			catch (SQLException e)
			{
				LOGGER.error("failed to insert time record {}.", timeRecord, e);
			}
		}
	}
}
