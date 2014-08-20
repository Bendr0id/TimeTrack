package com.vidi.timetrack.fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Fragment;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.vidi.timetrack.R;
import com.vidi.timetrack.api.TimeTrackMeClient;

@EFragment(R.layout.fr_home)
public class HomeFragment extends Fragment
{
	@Bean
	TimeTrackMeClient timeTrackMeClient;

	@ViewById
	ToggleButton toggleOnTrackButton;

	@ViewById
	TextView stateView;

	@AfterViews
	void loadView()
	{
		updateOnTrackState();
	}

	@Background
	void updateOnTrackState()
	{
		showOnTrackState(timeTrackMeClient.isOnTrack());
	}

	@UiThread
	void showOnTrackState(boolean state)
	{
		toggleOnTrackButton.setChecked(!state);

		if (state)
		{
			stateView.setText(R.string.onTrack);
		}
		else
		{
			stateView.setText(R.string.notOnTrack);
		}
	}

	@CheckedChange
	@Background
	void toggleOnTrackButton(CompoundButton button, boolean isChecked)
	{
		if (isChecked)
		{
			timeTrackMeClient.checkout();
		}
		else
		{
			timeTrackMeClient.checkin();
		}
		
		updateOnTrackState();
	}
}
