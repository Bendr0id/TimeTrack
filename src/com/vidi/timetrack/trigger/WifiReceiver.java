package com.vidi.timetrack.trigger;

import java.sql.SQLException;

import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.j256.ormlite.dao.Dao;
import com.vidi.timetrack.db.DatabaseHelper;
import com.vidi.timetrack.db.entities.Record;

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

	@OrmLiteDao(helper = DatabaseHelper.class, model = Record.class)
	Dao<Record, Integer> recordDao;
	
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
			}
			else if (state == NetworkInfo.State.DISCONNECTED)
			{
				LOGGER.info("Disconnected from ssid: {}", lastConnectedSSID);
			}
		}
	}
}
