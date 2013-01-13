package com.vidi.timetrack.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bluetoothLocation")
public class BluetoothLocation
{
	@DatabaseField(generatedId = true)
	public Integer id;

	@DatabaseField
	private String name;
	@DatabaseField
	private String mac;

	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Location locationList;

	public BluetoothLocation()
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMac()
	{
		return mac;
	}

	public void setMac(String mac)
	{
		this.mac = mac;
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
		return String.format("BluetoothLocation [id=%s, name=%s, mac=%s, locationList=%s]", id, name, mac, locationList);
	}
}
