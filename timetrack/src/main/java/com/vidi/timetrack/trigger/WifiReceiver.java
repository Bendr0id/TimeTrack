package com.vidi.timetrack.trigger;

import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.api.TimeTrackMeClient;
import com.vidi.timetrack.db.DatabaseHelper;
import com.vidi.timetrack.db.entities.Record;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.res.StringRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

@EReceiver
public class WifiReceiver extends BroadcastReceiver
{
	private final static Logger LOGGER = LoggerFactory.getLogger(WifiReceiver.class);

	@SystemService
	WifiManager wifiManager;

	@Bean
	TimeTrackMeClient timeTrackMeClient;

	@OrmLiteDao(helper = DatabaseHelper.class, model = Record.class)
	Dao<Record, Integer> recordDao;

    @StringRes
    String hardcodedSsid;

	private static String lastConnectedSSID;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		String action = intent.getAction();

		LOGGER.info("onReceive({})", action);

		if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION))
		{
			NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			NetworkInfo.State state = networkInfo.getState();

			if (state == NetworkInfo.State.CONNECTED)
			{
				lastConnectedSSID = wifiManager.getConnectionInfo().getSSID().replace("\"", "");

				LOGGER.info("Connected to ssid: {}", lastConnectedSSID);

				checkNetworkConnected(lastConnectedSSID);
			}
			else if (state == NetworkInfo.State.DISCONNECTED)
			{
				LOGGER.info("Disconnected from ssid: {}", lastConnectedSSID);

				checkNetworkDisconnected(lastConnectedSSID);

                lastConnectedSSID = null;
	    	}
			else
			{
				LOGGER.error("Unknown network state: {}", state);
			}
		}
	}

	@Background(delay = 10000)
	void checkNetworkDisconnected(String disconnectedSSID)
	{
        LOGGER.info("checkNetworkDisconnected");

		if (disconnectedSSID != null && disconnectedSSID.equals(hardcodedSsid))
		{
            LOGGER.info("Triggering checkout: {}", timeTrackMeClient.checkout());
		}
	}

	@Background
	void checkNetworkConnected(String connectedSSID)
	{
        LOGGER.info("checkNetworkConnected");

        if (connectedSSID != null && connectedSSID.equals(hardcodedSsid))
		{
            LOGGER.info("Triggering checkin: {}", timeTrackMeClient.checkin());
		}
	}
}
