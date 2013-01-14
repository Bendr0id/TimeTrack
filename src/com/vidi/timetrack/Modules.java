package com.vidi.timetrack;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.vidi.timetrack.db.Database;
import com.vidi.timetrack.db.entities.BluetoothLocation;
import com.vidi.timetrack.db.entities.GpsLocation;
import com.vidi.timetrack.db.entities.Location;
import com.vidi.timetrack.db.entities.NfcLocation;
import com.vidi.timetrack.db.entities.Record;
import com.vidi.timetrack.db.entities.WifiLocation;
import com.vidi.timetrack.db.provider.DaoProvider;
import com.vidi.timetrack.db.provider.DatabaseProvider;
import com.vidi.timetrack.db.provider.TransactionManagerProvider;

public class Modules extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(Database.class).toProvider(new DatabaseProvider());

		bind(TransactionManager.class).toProvider(new TransactionManagerProvider());

		bind(new TypeLiteral<Dao<Record, Integer>>()
		{}).toProvider(new DaoProvider<Dao<Record, Integer>, Record>(Record.class));

		bind(new TypeLiteral<Dao<Location, Integer>>()
		{}).toProvider(new DaoProvider<Dao<Location, Integer>, Location>(Location.class));

		bind(new TypeLiteral<Dao<WifiLocation, Integer>>()
		{}).toProvider(new DaoProvider<Dao<WifiLocation, Integer>, WifiLocation>(WifiLocation.class));

		bind(new TypeLiteral<Dao<GpsLocation, Integer>>()
		{}).toProvider(new DaoProvider<Dao<GpsLocation, Integer>, GpsLocation>(GpsLocation.class));

		bind(new TypeLiteral<Dao<BluetoothLocation, Integer>>()
		{}).toProvider(new DaoProvider<Dao<BluetoothLocation, Integer>, BluetoothLocation>(BluetoothLocation.class));

		bind(new TypeLiteral<Dao<NfcLocation, Integer>>()
		{}).toProvider(new DaoProvider<Dao<NfcLocation, Integer>, NfcLocation>(NfcLocation.class));
	}
}
