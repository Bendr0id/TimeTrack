package com.vidi.timetrack.db.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;
import com.vidi.timetrack.db.Database;

public class TransactionManagerProvider implements Provider<TransactionManager>
{
	@Inject
	private Provider<Database> databaseProvider;

	@Override
	public TransactionManager get()
	{
		ConnectionSource connectionSource = databaseProvider.get().getConnectionSource();
		return new TransactionManager(connectionSource);
	}
}
