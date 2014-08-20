package com.vidi.timetrack.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "wifiLocation")
public class WifiLocation
{
	@DatabaseField(generatedId = true)
	public Integer id;

	@DatabaseField
	private String ssid;
	@DatabaseField
	private String bssid;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Location locationList;

	public WifiLocation()
	{

	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getSsid()
	{
		return ssid;
	}

	public void setSsid(String ssid)
	{
		this.ssid = ssid;
	}

	public String getBssid()
	{
		return bssid;
	}

	public void setBssid(String bssid)
	{
		this.bssid = bssid;
	}

	public Location getLocationList()
	{
		return locationList;
	}

	public void setLocationList(Location locationList)
	{
		this.locationList = locationList;
	}

	@Override
	public String toString()
	{
		return String.format("WifiLocation [id=%s, ssid=%s, bssid=%s, locationList=%s]", id, ssid, bssid, locationList);
	}
}
