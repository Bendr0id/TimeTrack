package com.vidi.timetrack;

import java.util.Random;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.vidi.timetrack.activities.ScanActivity;

public class LoginTask extends RoboAsyncTask<Boolean>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginTask.class);
	private static final int SLEEP_TIME = 2000;

	@InjectView(R.id.login_form)
	private View loginView;

	@InjectView(R.id.login_status)
	private View statusView;

	@InjectView(R.id.password)
	private TextView passwordField;

	@InjectResource(R.string.error_incorrect_password)
	private String errorIncorrectPassword;

	@InjectResource(android.R.integer.config_shortAnimTime)
	private int shortAnimTime;

	@Inject
	private Activity activity;

	@Inject
	private Random random;

	@Inject
	public LoginTask(Context context)
	{
		super(context);
	}

	@Override
	public Boolean call() throws Exception
	{
		LOGGER.info("starting login task");
		showProgress(true);

		try
		{
			LOGGER.info("sleeping {}ms to simulate login.", SLEEP_TIME);
			Thread.sleep(SLEEP_TIME);
		}
		catch (InterruptedException e)
		{
			LOGGER.error("InterruptedException while sleeping {}ms in call. e: {}", SLEEP_TIME, e);
		}

		boolean loginResult = random.nextBoolean();

		LOGGER.info("simulated login was: {}", loginResult);
		return loginResult;
	}

	@Override
	protected void onSuccess(Boolean success) throws Exception
	{
		showProgress(false);

		if (success)
		{
			LOGGER.info("login was successfull, starting ScanActivity.");
			Intent intent = new Intent(activity, ScanActivity.class);
			activity.startActivity(intent);
			activity.finish();
		}
		else
		{
			LOGGER.info("login was not successfull, display incorrect password error.");
			activity.runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					passwordField.setError(errorIncorrectPassword);
					passwordField.requestFocus();
				}
			});
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show)
	{
		activity.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
				{
					statusView.setVisibility(View.VISIBLE);
					statusView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter()
					{
						@Override
						public void onAnimationEnd(Animator animation)
						{
							statusView.setVisibility(show ? View.VISIBLE : View.GONE);
						}
					});

					loginView.setVisibility(View.VISIBLE);
					loginView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter()
					{
						@Override
						public void onAnimationEnd(Animator animation)
						{
							loginView.setVisibility(show ? View.GONE : View.VISIBLE);
						}
					});
				}
				else
				{
					statusView.setVisibility(show ? View.VISIBLE : View.GONE);
					loginView.setVisibility(show ? View.GONE : View.VISIBLE);
				}

			}
		});
	}
}
