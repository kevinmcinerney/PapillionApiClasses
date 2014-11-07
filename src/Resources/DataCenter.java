package Resources;


import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import Connection.Connection;


public class DataCenter {
	private int dcid;
	@SuppressWarnings("unused")
	private ArrayList<Floor> floors = new ArrayList<Floor>();
	public static ArrayList<DataCenter> dataCenters = new ArrayList<DataCenter>();
	
	public DataCenter(int id) throws JSONException{
		this.dcid = id;
		this.floors = Floor.getFloors(dcid);
	}
	
	
	
	@SuppressWarnings("unused")
	public static ArrayList<DataCenter> getDataCenters() throws JSONException{	
		
		Connection con = new Connection();
		JSONObject results = con.connect("datacenters");
		JSONObject arr = results.optJSONObject("datacenter");
		System.out.println(results);
		if (arr != null) {
			for (int i = 0; i < results.length(); i++) {
				DataCenter dc = new DataCenter(arr.getInt("id"));
				dataCenters.add(dc);
			}
		
		}
		return dataCenters;
	}
	
	public ArrayList<Floor> getFloors() {
		return floors;
	}

	public int getId() {
		return dcid;
	}
}