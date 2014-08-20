package com.vidi.timetrack.db;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.common.collect.Lists;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.vidi.timetrack.db.entities.BluetoothLocation;
import com.vidi.timetrack.db.entities.GpsLocation;
import com.vidi.timetrack.db.entities.Location;
import com.vidi.timetrack.db.entities.NfcLocation;
import com.vidi.timetrack.db.entities.Record;
import com.vidi.timetrack.db.entities.WifiLocation;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
	private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

	private static final String DATABASE_NAME = "timetrack.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
	{
		try
		{
			for (Class<?> clazz : getDatabaseTableList())
			{
				TableUtils.createTable(connectionSource, clazz);
			}
		}
		catch (SQLException e)
		{
			LOGGER.error(getClass().getName(), "Could not create the tables.", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
	{
		try
		{
			for (Class<?> clazz : getDatabaseTableList())
			{
				TableUtils.dropTable(connectionSource, clazz, true);
			}
			onCreate(database, connectionSource);
		}
		catch (SQLException e)
		{
			LOGGER.error(getClass().getName(), "Could not upgrade the tables.", e);
		}
	}

	private List<Class<?>> getDatabaseTableList()
	{
		return Lists.<Class<?>> newArrayList(Record.class, Location.class, GpsLocation.class, WifiLocation.class, NfcLocation.class, BluetoothLocation.class);
	}
}
