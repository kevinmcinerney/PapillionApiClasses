import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONException;

import Resources.DataCenter;
import Resources.Floor;
import Resources.Host;
import Resources.Rack;

public class Test {
	
	public static void main(String[] args) throws JSONException{
		
			ArrayList<DataCenter> results;
			results = DataCenter.getDataCenters();
			
			for(DataCenter dc: results){
				for(Floor f: dc.getFloors()){
					for(Rack rk: f.getRacks()){
						for(Host h: rk.getHosts()){
							for(Long keyy: h.getPowerRates().keySet()){
								System.out.println(keyy + " " + h.getPowerRates().get(keyy) + " " + h.getHostId() + " date is " + dateFormat(keyy));
							}
						}
					}
					
				}
			}	
		
	}
	
	public static String dateFormat(Long timestamp){
		Date date = new Date(timestamp*1000L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + " " + "MM" + " " + "dd" + " " + "HH" + " " + "mm" + " "+ "ss" + " " + "SSS");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
		String formattedDate = sdf.format(date);
		
		return formattedDate;
		
	}
}
