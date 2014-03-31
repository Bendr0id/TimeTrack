package com.vidi.timetrack.wizard;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.StringRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Fragment;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.R;
import com.vidi.timetrack.db.DatabaseHelper;
import com.vidi.timetrack.db.entities.Location;

@EFragment(R.layout.fragment_geofence_wizard)
public class GeofenceFragment extends Fragment
{
	private final static Logger LOGGER = LoggerFactory.getLogger(GeofenceFragment.class);

	private final static LatLng DDORF = new LatLng(51.2249429, 6.7756524);

	@OrmLiteDao(helper = DatabaseHelper.class, model = Location.class)
	Dao<Location, Integer> locationDao;

	@StringRes
	String locationWizardTitle;

	@StringArrayRes
	String[] locationItems;

	@FragmentById(R.id.map)
	MapFragment mapFragment;

	private GoogleMap googleMap;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@AfterViews
	void loadView()
	{
		googleMap = mapFragment.getMap();
		
		Marker ddorf = googleMap.addMarker(new MarkerOptions().position(DDORF).title("Düsseldorf"));

		/*
		 * MapFragment mapFragment = MapFragment.newInstance();
		 * FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		 * fragmentTransaction.add(R.id.map, mapFragment);
		 * fragmentTransaction.commit();
		 * 
		 * getFragmentManager().beginTransaction();
		 */
	}
}
