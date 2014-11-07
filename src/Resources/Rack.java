package Resources;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import Connection.Connection;


public class Rack {
	private int dcid;
	private int floorid;
	private int rackid;
	private static ArrayList<Host> hosts = new ArrayList<Host>();
	public static ArrayList<Rack> racks = new ArrayList<Rack>();


	public Rack(int dcid, int floorid, int rackid) throws JSONException {
		this.dcid = dcid;
		this.floorid = floorid;
		this.rackid = rackid;
		this.hosts = Host.getHosts(dcid, floorid, rackid);
	}

	public static ArrayList<Rack> getRacks(int dcid, int floorid) throws JSONException{
		// use id to get datacenter id and floorid to get fl id
		String APICall = "datacenters/" + dcid + "/floors/" + floorid + "/racks";
		Connection con = new Connection();
		JSONObject results = con.connect(APICall);
		
		JSONObject arr = results.optJSONObject("rack");
	
		if (arr != null) {
			// System.out.println(arr.toString(4));
			System.out.println("rack results length: " + results.length());
	
			for (int i = 0; i < results.length(); i++) {
				
				//dcid = arr.getInt("datacenterId");
				floorid = arr.getInt("floorId");
				
				racks.add(new Rack(dcid,floorid, arr.getInt("id")));
			}
		}
		return racks;
	}

	public int getDcid() {
		return dcid;
	}

	public int getFloorid() {
		return floorid;
	}

	public int getRackid() {
		return rackid;
	}
	
	public static ArrayList<Host> getHosts() {
		return hosts;
	}
	public static ArrayList<Rack> getRacks() {
		return racks;
	}
}