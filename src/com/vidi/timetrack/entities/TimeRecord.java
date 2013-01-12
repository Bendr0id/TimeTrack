package com.vidi.timetrack.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "timeRecord")
public class TimeRecord
{
	@DatabaseField(id = true, generatedId = true)
	private int id;

	@DatabaseField
	private String begin;

	@DatabaseField
	private String end;

	@DatabaseField
	private Location location;

	public TimeRecord()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBegin()
	{
		return begin;
	}

	public void setBegin(String begin)
	{
		this.begin = begin;
	}

	public String getEnd()
	{
		return end;
	}

	public void setEnd(String end)
	{
		this.end = end;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	@Override
	public String toString()
	{
		return String.format("TimeRecord [id=%s, begin=%s, end=%s, location=%s]", id, begin, end, location);
	}
}
