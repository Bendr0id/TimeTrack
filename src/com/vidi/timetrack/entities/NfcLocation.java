package com.vidi.timetrack.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "nfcLocation")
public class NfcLocation
{
	@DatabaseField(id = true, generatedId = true)
	private int id;

	@DatabaseField
	private String tagValue;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Location locationList;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
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
