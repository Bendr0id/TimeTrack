package com.vidi.timetrack.api;

import com.vidi.timetrack.Settings_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@EBean(scope = Scope.Singleton)
public class TimeTrackMeClient
{
	@RestService
	TimeTrackMe timeTrackMe;

    @Pref
    Settings_ settings;

    public TimeTrackMeClient()
	{

	}

	public Map<String, String> login(String user, String password)
	{
		timeTrackMe.setHttpBasicAuth(user, password);

		return triggerStatus();
	}


	public Map<String, String> checkin()
	{
        timeTrackMe.setHttpBasicAuth(settings.email().get(), settings.password().get());

        return triggerAction("1");
	}

	public Map<String, String> checkout()
	{
        timeTrackMe.setHttpBasicAuth(settings.email().get(), settings.password().get());

        return triggerAction("0");
	}

	public boolean isOnTrack()
	{
        timeTrackMe.setHttpBasicAuth(settings.email().get(), settings.password().get());

        return triggerStatus().get("status").equals("in");
	}
	
	private Map<String, String> triggerStatus()
	{
		return timeTrackMe.status();
	}

	private Map<String, String> triggerAction(String entry)
	{
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("entry", entry);

		Map<String, String> response = timeTrackMe.action(form);

		return response;
	}
}
