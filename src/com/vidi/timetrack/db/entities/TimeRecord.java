package com.vidi.timetrack.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "timeRecord")
public class TimeRecord
{
	@DatabaseField(generatedId = true)
	public Integer id;

	@DatabaseField
	private Long begin;

	@DatabaseField
	private Long end;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Location location;

	public TimeRecord()
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

	public Long getBegin()
	{
		return begin;
	}

	public void setBegin(Long begin)
	{
		this.begin = begin;
	}

	public Long getEnd()
	{
		return end;
	}

	public void setEnd(Long end)
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
