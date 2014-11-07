package Resources;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Connection.Connection;
@SuppressWarnings("unused")
public class PowerStamp {
	
	private int dcid;
	private int floorid;
	private int rackid;
	private int hostid;
	private String serverid;
	private static HashMap<Long, Double> powerRates = new HashMap<Long, Double>();
	
	public PowerStamp(int dcid, int floorid, int rackid, int hostid)	{
		this.dcid = dcid;
		this.floorid = floorid;
		this.rackid = rackid;
		this.hostid = hostid;
	}
	
	public static HashMap<Long, Double> getPower(int dcid, int floorid, int rackid, int hostid)
			throws JSONException {
		long startTime = (long) (System.currentTimeMillis() / 1000 - (1 *  31536000)); // year ago
		long endTime = System.currentTimeMillis() / 1000;
		String APICall = "datacenters/" + dcid + "/floors/" + floorid + "/racks/" + rackid
				+ "/hosts/" + hostid + "/power?starttime=" + startTime + "&endtime=" + endTime;
		
		Connection con = new Connection();
		JSONObject results = con.connect(APICall);
		JSONArray arr = results.getJSONArray("power");
		System.out.println("power results length: " + arr.length());
		
		double total = 0;
		double power = 0;
		long timestamp = 0;
		
		for (int i = 0; i <= arr.length()-1; i++) {
			JSONObject p = arr.getJSONObject(i);
			power = p.getDouble("power");
			timestamp = p.getLong("timeStamp");
			total += power;
			//System.out.println(hostid + "  " + power);	
			powerRates.put(timestamp, power);
			
		}
		
		double average = total/arr.length();
		
		
		System.out.println("added power rating");
			
		System.out.println(average);
		System.out.println(total);

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
}