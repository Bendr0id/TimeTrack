package com.vidi.timetrack.db.provider;

import java.sql.SQLException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.dao.Dao;

public class DaoProvider<D extends Dao<E, ?>, E> implements Provider<D>
{

	@Inject
	private DatabaseProvider databaseProvider;

	private final Class<E> cls;

	public DaoProvider(Class<E> cls)
	{
		this.cls = cls;
	}

	@Override
	public D get()
	{
		try
		{
			return databaseProvider.get().getDao(cls);
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
}
