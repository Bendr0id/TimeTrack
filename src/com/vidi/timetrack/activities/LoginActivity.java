package com.vidi.timetrack.activities;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.vidi.timetrack.LoginTask;
import com.vidi.timetrack.R;

@ContentView(R.layout.activity_login)
public class LoginActivity extends RoboActivity implements OnEditorActionListener, OnClickListener
{
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginActivity.class);

	@InjectView(R.id.username)
	private EditText usernameField;
	@InjectView(R.id.password)
	private EditText passwordField;
	@InjectView(R.id.sign_in_button)
	private Button signinButton;
	@InjectView(R.id.login_status_message)
	private TextView statusMessageView;

	@InjectResource(R.string.error_invalid_password)
	private String errorInvalidPassword;
	@InjectResource(R.string.error_invalid_username)
	private String errorInvalidUsername;
	@InjectResource(R.string.error_incorrect_password)
	private String errorIncorrectPassword;
	@InjectResource(R.string.error_field_required)
	private String errorFieldRequired;
	@InjectResource(R.string.login_progress_signing_in)
	private String loginProgressMessage;

	@Inject
	private LoginTask loginTask;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		passwordField.setOnEditorActionListener(this);
		signinButton.setOnClickListener(this);
	}

	@Override
	public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
	{
		if (textView == passwordField)
		{
			attemptLogin();
			return true;
		}

		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public void onClick(View view)
	{
		if (view == signinButton)
		{
			attemptLogin();
		}
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
			loginTask.execute();
		}
	}
}
