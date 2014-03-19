package com.vidi.timetrack.tabs;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.StringRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.R;
import com.vidi.timetrack.db.DatabaseHelper;
import com.vidi.timetrack.db.entities.Location;

@EFragment(R.layout.fragment_location)
@OptionsMenu(R.menu.fragment_location)
public class LocationFragment extends SherlockFragment
{
	private final static Logger LOGGER = LoggerFactory.getLogger(LocationFragment.class);

	@OrmLiteDao(helper = DatabaseHelper.class, model = Location.class)
	Dao<Location, Integer> locationDao;

	@ViewById
	Button addLocationButton;

	@StringRes
	String locationWizardTitle;

	@StringArrayRes
	String[] locationItems;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@OptionsItem
	void menuAddLocation()
	{
		addLocationButton();
	}

	@Click
	void addLocationButton()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle(locationWizardTitle);
		builder.setItems(locationItems, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				switch (which)
				{
					case 0:
						showWifiWizardFragment();
						break;
					default:
						break;
				}
			}
		});

		builder.create().show();
	}

	private void showWifiWizardFragment()
	{
		LOGGER.debug("will show wifi wizard.");
	}
}
