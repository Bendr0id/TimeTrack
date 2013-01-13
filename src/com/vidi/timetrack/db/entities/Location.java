package com.vidi.timetrack.db.entities;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "location")
public class Location
{
	@DatabaseField(generatedId = true)
	public Integer id;

	@DatabaseField
	private String name;

	@ForeignCollectionField
	private ForeignCollection<WifiLocation> wifiLocations;

	@ForeignCollectionField
	private ForeignCollection<GpsLocation> gpsLocations;

	@ForeignCollectionField
	private ForeignCollection<NfcLocation> nfcLocations;

	@ForeignCollectionField
	private ForeignCollection<BluetoothLocation> bluetoothLocations;

	public Location()
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

	public ForeignCollection<WifiLocation> getWifiLocations()
	{
		return wifiLocations;
	}

	public void setWifiLocations(ForeignCollection<WifiLocation> wifiLocations)
	{
		this.wifiLocations = wifiLocations;
	}

	public ForeignCollection<GpsLocation> getGpsLocations()
	{
		return gpsLocations;
	}

	public void setGpsLocations(ForeignCollection<GpsLocation> gpsLocations)
	{
		this.gpsLocations = gpsLocations;
	}

	public ForeignCollection<NfcLocation> getNfcLocations()
	{
		return nfcLocations;
	}

	public void setNfcLocations(ForeignCollection<NfcLocation> nfcLocations)
	{
		this.nfcLocations = nfcLocations;
	}

	public ForeignCollection<BluetoothLocation> getBluetoothLocations()
	{
		return bluetoothLocations;
	}

	public void setBluetoothLocations(ForeignCollection<BluetoothLocation> bluetoothLocations)
	{
		this.bluetoothLocations = bluetoothLocations;
	}
}
