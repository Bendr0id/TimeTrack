package com.vidi.timetrack.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.vidi.timetrack.db.entities.BluetoothLocation;
import com.vidi.timetrack.db.entities.GpsLocation;
import com.vidi.timetrack.db.entities.Location;
import com.vidi.timetrack.db.entities.NfcLocation;
import com.vidi.timetrack.db.entities.Record;
import com.vidi.timetrack.db.entities.WifiLocation;

public class Database extends OrmLiteSqliteOpenHelper
{
	@Inject
	public Database(Context context)
	{
		super(context, "timetrack.db", null, 8);
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
			throw new RuntimeException(e);
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
			throw new RuntimeException(e);
		}
	}

	private List<Class<?>> getDatabaseTableList()
	{
		return Lists.<Class<?>> newArrayList(Record.class, Location.class, GpsLocation.class, WifiLocation.class, NfcLocation.class, BluetoothLocation.class);
	}
}
