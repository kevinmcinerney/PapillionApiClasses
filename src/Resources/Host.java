package Resources;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import Connection.Connection;

//create one connection and use that connection for all calls
public class Host {

	private int dcid;
	private int floorid;
	private int rackid;
	private int hostid;
	@SuppressWarnings("unused")
	private HashMap<Long, Double> powerRates;
	
	public static ArrayList<Host> hosts = new ArrayList<Host>();


	public Host(int dcid, int floorid, int rackid, int hostid) throws JSONException {
		this.dcid = dcid;
		this.floorid = floorid;
		this.rackid = rackid;
		this.hostid = hostid;
		this.powerRates = PowerStamp.getPower(dcid, floorid, rackid, hostid);
	}

	public static ArrayList<Host> getHosts(int dcid, int floorid, int rackid) throws JSONException {
	
		String APICall = "datacenters/" + dcid + "/floors/" + floorid + "/racks/" + rackid
				+ "/hosts/";
		Connection con = new Connection();
		JSONObject results = con.connect(APICall);
		ArrayList<Host> hosts = new ArrayList<Host>();
		JSONObject arr = results.optJSONObject("host");

		if (arr != null) {
			System.out.println("host results length: " + results.length());

			for (int i = 0; i <= results.length() - 1; i++) {
				
				hosts.add(new Host(dcid, floorid, arr.getInt("rackId"), arr.getInt("id")));
				System.out.println("added host");
			}
		}
		return hosts;
	}

	public int getHostId() {
		return hostid;
	}
	
	public HashMap<Long, Double> getPowerRates(){
		return powerRates;
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

	public int getHostid() {
		return hostid;
	}
	
	public static ArrayList<Host> getHosts() {
		return hosts;
	}
	
}