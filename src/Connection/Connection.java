package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;


public class Connection {
	private String baseUrl;
	private String httpMethod;
	private String returnType;
	private String data;
	private HttpURLConnection connection;
	
	public Connection()	{
		this.baseUrl= "http://localhost:8080/papillonserver/rest/";
		this.httpMethod = "GET";
		this.returnType = "application/json";
	}
	
	public JSONObject connect(String APICall) throws JSONException {
		String restReq = baseUrl + APICall;
		
		
		try {
			connection = (HttpURLConnection) (new URL(restReq))
					.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod(httpMethod);
			connection.setRequestProperty("Accept", returnType);
			connection.setRequestProperty("Content-Type", returnType);

			String responseMsg = connection.getResponseMessage();

			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				// something went wrong
				return new JSONObject(responseMsg);
				
			} else {
				StringBuffer sb = new StringBuffer();

				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null)
					sb.append(inputLine);
				in.close();

				data = sb.toString();
				System.out.println("API call sent: " + restReq); //remove after testing
				return new JSONObject(data);

			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new JSONObject("MalformedURLException");
		} catch (IOException e) {
			e.printStackTrace();
			return new JSONObject("IOException");
		}

	}
}