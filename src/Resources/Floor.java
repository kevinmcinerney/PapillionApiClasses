package Resources;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import Connection.Connection;

public class Floor {
	private int dcid;
	private int floorid;
	private ArrayList<Rack> racks;
	
	public static ArrayList<Floor> floors = new ArrayList<Floor>();

	
	public Floor(int dcid, int floorid) throws JSONException {
		this.dcid = dcid;
		this.floorid = floorid;
		this.racks = Rack.getRacks(dcid, floorid);
	}

	public ArrayList<Rack> getRacks() {
		return racks;
	}

	public static ArrayList<Floor> getFloors(int dcid) throws JSONException{
		String APICall = "datacenters/" + dcid + "/floors";
		Connection con = new Connection();
		JSONObject results = con.connect(APICall); 
		JSONObject arr = results.optJSONObject("floor");
		
		if (arr != null) {
	
			System.out.println("floor results length: " + results.length());
			// System.out.println(arr.toString(4));
	
			for (int i = 0; i < results.length(); i++) {
				dcid = arr.getInt("datacenterId");
	
				floors.add(new Floor(dcid, arr.getInt("id")));
			}
		
		}
	return floors;
	}

	public int getFloorid() {
		return floorid;
	}

	public int getDcid() {
		return dcid;
	}
	
	
}