package com.vidi.timetrack.api;

import java.util.Map;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresAuthentication;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.MultiValueMap;

@Rest(rootUrl = "https://timetrack.me/api", converters = { FormHttpMessageConverter.class, MappingJacksonHttpMessageConverter.class })
public interface TimeTrackMe
{
	void setHttpBasicAuth(String username, String password);

	@Post("/action")
	@RequiresAuthentication
	@Accept(MediaType.APPLICATION_JSON)
	Map<String, String> action(MultiValueMap<String, String> form);

	@Get("/status")
	@RequiresAuthentication
	@Accept(MediaType.APPLICATION_JSON)
	Map<String, String> status();

}
