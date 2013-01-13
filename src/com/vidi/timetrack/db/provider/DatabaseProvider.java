package com.vidi.timetrack.db.provider;

import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.vidi.timetrack.db.Database;

public class DatabaseProvider implements Provider<Database>
{
	@Inject
	private Provider<Context> contextProvider;

	@Override
	public Database get()
	{
		Context context = contextProvider.get();
		return OpenHelperManager.getHelper(context, Database.class);
	}
}
