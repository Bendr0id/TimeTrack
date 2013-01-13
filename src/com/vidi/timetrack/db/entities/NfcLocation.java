package com.vidi.timetrack.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "nfcLocation")
public class NfcLocation
{
	@DatabaseField(generatedId = true)
	public Integer id;

	@DatabaseField
	private String tagValue;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Location locationList;

	public NfcLocation()
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

	public String getTagValue()
	{
		return tagValue;
	}

	public void setTagValue(String tagValue)
	{
		this.tagValue = tagValue;
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
		return String.format("NfcLocation [id=%s, tagValue=%s, locationList=%s]", id, tagValue, locationList);
	}
}
