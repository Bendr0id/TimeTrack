package com.vidi.timetrack.api;

import java.util.Map;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@EBean(scope = Scope.Singleton)
public class TimeTrackMeClient
{
	@RestService
	TimeTrackMe timeTrackMe;

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
		return triggerAction("1");
	}

	public Map<String, String> checkout()
	{
		return triggerAction("0");
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
