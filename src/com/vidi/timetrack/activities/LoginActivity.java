package com.vidi.timetrack.activities;

import java.util.Map;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.IntegerRes;
import org.androidannotations.annotations.res.StringRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.vidi.timetrack.R;
import com.vidi.timetrack.api.TimeTrackMeClient;

@EActivity(R.layout.activity_login)
@OptionsMenu(R.menu.activity_login)
public class LoginActivity extends SherlockActivity implements OnEditorActionListener
{
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginActivity.class);

	@ViewById(R.id.login_form)
	View loginView;
	@ViewById(R.id.login_status)
	View statusView;

	@ViewById(R.id.username)
	EditText usernameField;
	@ViewById(R.id.password)
	EditText passwordField;
	@ViewById(R.id.sign_in_button)
	Button signInButton;
	@ViewById(R.id.login_status_message)
	TextView statusMessageView;

	@StringRes(R.string.error_invalid_password)
	String errorInvalidPassword;
	@StringRes(R.string.error_invalid_username)
	String errorInvalidUsername;
	@StringRes(R.string.error_incorrect_password)
	String errorIncorrectPassword;
	@StringRes(R.string.error_field_required)
	String errorFieldRequired;
	@StringRes(R.string.login_progress_signing_in)
	String loginProgressMessage;

	@IntegerRes(android.R.integer.config_shortAnimTime)
	int shortAnimTime;

	@Bean
	TimeTrackMeClient timeTrackMeClient;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@AfterViews
	public void addOnActionListener()
	{
		passwordField.setOnEditorActionListener(this);
	}

	public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
	{
		if (textView == passwordField)
		{
			attemptLogin();
			return true;
		}

		return false;
	}

	@Click
	public void signInButton()
	{
		attemptLogin();
	}

	public void attemptLogin()
	{
		LOGGER.info("attempt to login.");

		usernameField.setError(null);
		passwordField.setError(null);

		String username = usernameField.getText().toString();
		String password = passwordField.getText().toString();

		View focusView = null;

		if (TextUtils.isEmpty(password))
		{
			LOGGER.info("password is empty. will focus passwordField.");
			passwordField.setError(errorFieldRequired);
			focusView = passwordField;
		}
		else if (password.length() < 4)
		{
			LOGGER.info("password is to short. will focus passwordField.");
			passwordField.setError(errorInvalidPassword);
			focusView = passwordField;
		}

		if (TextUtils.isEmpty(username))
		{
			LOGGER.info("username is empty. will focus usernameField.");
			usernameField.setError(errorFieldRequired);
			focusView = usernameField;
		}
		else if (username.length() < 2)
		{
			LOGGER.info("username is to short. will focus usernameField.");
			usernameField.setError(errorInvalidUsername);
			focusView = usernameField;
		}

		if (focusView != null)
		{
			focusView.requestFocus();
		}
		else
		{
			LOGGER.info("trying to login. starting loginTask");
			statusMessageView.setText(loginProgressMessage);
			login();
		}
	}

	@Background
	public void login()
	{
		showProgress(true);

		try
		{
			Map<String, String> response = timeTrackMeClient.login(usernameField.getText().toString(), passwordField.getText().toString());
			LOGGER.info("response: {}", response);
			loginSuccess();
		}
		catch (RestClientException e)
		{
			LOGGER.error("error: {}", e);
			loginFailed();
		}

		showProgress(false);
	}

	@UiThread
	void showProgress(final boolean show)
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

	@UiThread
	void loginSuccess()
	{
		LOGGER.info("login was successfull, starting ScanActivity.");

		MainTabActivity_.intent(this).start();
		finish();
	}

	@UiThread
	void loginFailed()
	{
		LOGGER.info("login was not successfull, display incorrect password error.");

		passwordField.setError(errorIncorrectPassword);
		passwordField.requestFocus();
	}
}