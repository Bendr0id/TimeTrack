package com.vidi.timetrack.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.vidi.timetrack.LoadingOverlay;
import com.vidi.timetrack.R;
import com.vidi.timetrack.Settings_;
import com.vidi.timetrack.api.TimeTrackMeClient;

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
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@EActivity(R.layout.ac_login)
@OptionsMenu(R.menu.login_context_menu)
public class LoginActivity extends Activity implements OnEditorActionListener
{
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginActivity.class);

	@ViewById
	EditText emailField;
	@ViewById
	EditText passwordField;
	@ViewById
	Button loginButton;

	@StringRes
	String errorInvalidPassword;
	@StringRes
	String errorInvalidUsername;
	@StringRes
	String errorIncorrectPassword;
	@StringRes
	String errorFieldRequired;
	@StringRes(R.string.login_progress_logging_in)
	String loginProgressMessage;

	@IntegerRes(android.R.integer.config_shortAnimTime)
	int shortAnimTime;

    @Bean
	TimeTrackMeClient timeTrackMeClient;

    @Pref
    Settings_ settings;

    private LoadingOverlay loadingOverlay;

    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@AfterViews
	public void addOnActionListener()
	{
        emailField.setText(settings.email().get());
        passwordField.setText(settings.password().get());
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
	public void loginButton()
	{
		attemptLogin();
	}

	public void attemptLogin()
	{
		LOGGER.info("attempt to login.");

		emailField.setError(null);
		passwordField.setError(null);

		String username = emailField.getText().toString();
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
			emailField.setError(errorFieldRequired);
			focusView = emailField;
		}
		else if (username.length() < 2)
		{
			LOGGER.info("username is to short. will focus usernameField.");
			emailField.setError(errorInvalidUsername);
			focusView = emailField;
		}

		if (focusView != null)
		{
			focusView.requestFocus();
		}
		else
		{
			LOGGER.info("trying to login. starting loginTask");
            login();
		}
	}

	@Background
	public void login()
	{
		showLoading();

		try
		{
			Map<String, String> response = timeTrackMeClient.login(emailField.getText().toString(), passwordField.getText().toString());
			LOGGER.info("response: {}", response);

            settings.email().put(emailField.getText().toString());
            settings.password().put(passwordField.getText().toString());

            loginSuccess();
		}
		catch (RestClientException e)
		{
			LOGGER.error("error: {}", e);

            settings.email().remove();
            settings.password().remove();

            loginFailed();
		}

		hideLoading();
	}

	@UiThread
	void showLoading()
	{
        if (loadingOverlay == null)
        {
            loadingOverlay = new LoadingOverlay(this);
        }

        loadingOverlay.show();
	}

    @UiThread
    void hideLoading()
    {
        if (loadingOverlay != null && loadingOverlay.isShowing())
        {
            loadingOverlay.dismiss();
        }
    }

	@UiThread
	void loginSuccess()
	{
		LOGGER.info("login was successfull, starting ScanActivity.");

		MainActivity_.intent(this).start();
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