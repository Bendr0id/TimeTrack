package com.vidi.timetrack.activities;

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

import com.vidi.timetrack.R;

@ContentView(R.layout.activity_timetrack)
public class TimeTrackActivity extends RoboActivity implements OnClickListener
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TimeTrackActivity.class);

	@InjectView(R.id.scan_button)
	private Button scanButton;

	@InjectView(R.id.scan_result_listview)
	private ListView scanResultListView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		scanButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		if (v == scanButton)
		{
			LOGGER.info("scan button was clicked.");
		}
	}
}
