package com.vidi.timetrack.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "gpsLocation")
public class GpsLocation
{
	@DatabaseField(generatedId = true)
	public Integer id;

	@DatabaseField
	private Double lat;
	@DatabaseField
	private Double lon;
	@DatabaseField
	private Double radius;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Location locationList;

	public GpsLocation()
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

	public Double getLat()
	{
		return lat;
	}

	public void setLat(Double lat)
	{
		this.lat = lat;
	}

	public Double getLon()
	{
		return lon;
	}

	public void setLon(Double lon)
	{
		this.lon = lon;
	}

	public Double getRadius()
	{
		return radius;
	}

	public void setRadius(Double radius)
	{
		this.radius = radius;
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
		return String.format("GpsLocation [id=%s, lat=%s, lon=%s, radius=%s, locationList=%s]", id, lat, lon, radius, locationList);
	}
}
