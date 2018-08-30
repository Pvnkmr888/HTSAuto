package com.honeywell.chapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.honeywell.commons.coreframework.SuiteConstants;
import com.honeywell.commons.coreframework.SuiteConstants.SuiteConstantTypes;
import com.honeywell.commons.coreframework.TestCaseInputs;

public class ChapiUtil implements AutoCloseable {

	/**
	 * @param args
	 */

	private String chapiURL;
	private boolean isConnected;
	private HttpURLConnection chapiConnection;
	private String cookie;
	private HashMap<String, Long> locations;
	private String verificationToken;
	private String bodyToken;
	private String sessionID;
	private TestCaseInputs inputs;

	public ChapiUtil(TestCaseInputs inputs) {
		try {
			if (inputs.getInputValue(TestCaseInputs.APP_ENVIRONMENT).equals("Production")) {
				this.chapiURL = SuiteConstants.getConstantValue(SuiteConstantTypes.PROJECT_SPECIFIC,
						"CHAPI_URL_PRODUCTION");
			} else if (inputs.getInputValue(TestCaseInputs.APP_ENVIRONMENT).equals("Jasper_QA")) {
				this.chapiURL = SuiteConstants.getConstantValue(SuiteConstantTypes.PROJECT_SPECIFIC,
						"CHAPI_URL_JASPER_QA");
			} else if (inputs.getInputValue(TestCaseInputs.APP_ENVIRONMENT).equals("Dogfooding")) {
				this.chapiURL = SuiteConstants.getConstantValue(SuiteConstantTypes.PROJECT_SPECIFIC,
						"CHAPI_URL_DOGFOODING");
			} else if (inputs.getInputValue(TestCaseInputs.APP_ENVIRONMENT).equals("Staging")) {
				this.chapiURL = SuiteConstants.getConstantValue(SuiteConstantTypes.PROJECT_SPECIFIC,
						"CHAPI_URL_STAGING");
			} else if (inputs.getInputValue(TestCaseInputs.APP_ENVIRONMENT).equals("Chil Das(QA)")) {
				chapiURL = SuiteConstants.getConstantValue(SuiteConstantTypes.PROJECT_SPECIFIC,
						"CHAPI_URL_CHIL_DAS_QA");
			}
			else if (inputs.getInputValue(TestCaseInputs.APP_ENVIRONMENT).equals("CHIL Int (Azure)")) {
				chapiURL = SuiteConstants.getConstantValue(SuiteConstantTypes.PROJECT_SPECIFIC,
						"CHIL_INT");
			}
			else if (inputs.getInputValue(TestCaseInputs.APP_ENVIRONMENT).equals("Chil Das(Test)")) {
				chapiURL = SuiteConstants.getConstantValue(SuiteConstantTypes.PROJECT_SPECIFIC,
						"CHIL_DAS_TEST");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.inputs = inputs;
		this.isConnected = false;
		locations = new HashMap<String, Long>();
	}

	public boolean isConnected() {
		return isConnected;
	}

	public HttpURLConnection getChapiConnection() {
		return chapiConnection;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public String getBodyToken() {
		return bodyToken;
	}

	public String getSessionID() {
		return sessionID;
	}

	public boolean getConnection() {
		try {
			URL url = new URL(this.chapiURL + "api/v2/Session");
			String input = "{\"username\": \"" + inputs.getInputValue("USERID") + "\",\"password\": \""
					+ inputs.getInputValue("PASSWORD") + "\"}";

			chapiConnection = (HttpURLConnection) url.openConnection();

			chapiConnection.setDoOutput(true);
			chapiConnection.setRequestMethod("POST");
			chapiConnection.setRequestProperty("content-type", "application/json");
			chapiConnection.setRequestProperty("content-length", String.valueOf(input.length()));

			OutputStream os = chapiConnection.getOutputStream();

			os.write(input.getBytes());
			os.flush();

			if (chapiConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				BufferedReader in = new BufferedReader(new InputStreamReader(chapiConnection.getInputStream()));

				String inputLine;
				StringBuffer html = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					html.append(inputLine);
				}

				in.close();

				JSONObject jsonObj = new JSONObject(html.toString().trim());

				cookie = chapiConnection.getHeaderField("Set-Cookie");

				bodyToken = jsonObj.get("bodytoken").toString();

				sessionID = jsonObj.get("sessionID").toString();

				verificationToken = chapiConnection.getHeaderField("RequestVerificationToken");

				JSONArray jsonUsers = (JSONArray) ((JSONObject) jsonObj.get("user")).get("locationRoleMapping");
				for (int i = 0; i < jsonUsers.length(); i++) {
					JSONObject tempJSON = (JSONObject) jsonUsers.get(i);
					locations.put(tempJSON.getString("locationName"), tempJSON.getLong("locationID"));
				}

				isConnected = true;
			} else {
				isConnected = false;
			}

		} catch (Exception e) {
			System.out.println("Error Occured: " + e.getMessage());
			isConnected = false;
		}
		return isConnected;
	}

	public boolean disconnect() {
		if (isConnected) {
			if (chapiConnection != null) {
				try {
					chapiConnection.disconnect();
					isConnected = false;
				} catch (Exception e) {
					isConnected = true;
				}

			} else {
				isConnected = false;
			}
		} else {
			isConnected = false;
		}

		return !isConnected;
	}

	public HttpURLConnection doPostRequest(String urlString, String headerData) {

		HttpURLConnection postResponse = null;
		try {
			URL url = new URL(urlString);
			postResponse = (HttpURLConnection) url.openConnection();

			postResponse.setRequestMethod("POST");
			postResponse.setDoOutput(true);

			postResponse.setRequestProperty("sessionID", getSessionID());
			postResponse.setRequestProperty("RequestVerificationToken", getVerificationToken() + ":" + getBodyToken());
			postResponse.setRequestProperty("Cookie", cookie);

			postResponse.setRequestProperty("content-type", "application/json");

			if (!headerData.equals("")) {
				postResponse.setRequestProperty("content-length", String.valueOf(headerData.length()));
				OutputStream os = postResponse.getOutputStream();
				os.write(headerData.getBytes("UTF-8"));
				os.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return postResponse;
	}

	public HttpURLConnection doGetRequest(String urlString) {
		HttpURLConnection getResponse = null;
		try {
			URL url = new URL(urlString);

			getResponse = (HttpURLConnection) url.openConnection();

			getResponse.setRequestProperty("sessionID", getSessionID());
			getResponse.setRequestProperty("RequestVerificationToken", getVerificationToken() + ":" + getBodyToken());
			getResponse.setRequestProperty("Cookie", cookie);
			getResponse.setDoOutput(true);
			getResponse.setRequestMethod("GET");

			if (getResponse.getResponseCode() == HttpURLConnection.HTTP_CREATED
					|| getResponse.getResponseCode() == HttpURLConnection.HTTP_OK) {

				// BufferedReader in = new BufferedReader(new InputStreamReader(
				// getResponse.getInputStream()));
				//
				// String inputLine;
				// StringBuffer html = new StringBuffer();
				//
				// while ((inputLine = in.readLine()) != null) {
				// html.append(inputLine);
				// }
				//
				//
				// System.out.println(html);

			} else {
				// System.out.println("Connection to Chapi is not successFul.
				// Response Code - " + getResponse.getResponseCode());
				// return null;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return getResponse;
	}

	public HttpURLConnection doDeleteRequest(String urlString) {
		HttpURLConnection getResponse = null;
		try {
			URL url = new URL(urlString);

			getResponse = (HttpURLConnection) url.openConnection();

			getResponse.setRequestProperty("sessionID", getSessionID());
			getResponse.setRequestProperty("RequestVerificationToken", getVerificationToken() + ":" + getBodyToken());
			getResponse.setRequestProperty("Cookie", cookie);
			getResponse.setDoOutput(true);
			getResponse.setRequestMethod("DELETE");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return getResponse;
	}

	public HttpURLConnection doPutRequest(String urlString, String headerData) {

		HttpURLConnection postResponse = null;
		try {
			URL url = new URL(urlString);
			postResponse = (HttpURLConnection) url.openConnection();

			postResponse.setRequestMethod("PUT");
			postResponse.setDoOutput(true);

			postResponse.setRequestProperty("sessionID", getSessionID());
			postResponse.setRequestProperty("RequestVerificationToken", getVerificationToken() + ":" + getBodyToken());
			postResponse.setRequestProperty("Cookie", cookie);

			postResponse.setRequestProperty("content-type", "application/json");

			// postResponse.connect();

			if (!headerData.equals("")) {
				postResponse.setRequestProperty("content-length", String.valueOf(headerData.length()));
				OutputStream os = postResponse.getOutputStream();
				os.write(headerData.getBytes("UTF-8"));
				os.flush();
			}

			if (postResponse.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				System.out.println("Success");
			} else {
				// System.out.println(postResponse.getResponseCode());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return postResponse;
	}

	@Override
	public void close() throws Exception {
		disconnect();

	}

	public Set<String> getLocationNames() {
		return locations.keySet();
	}

	public long getLocationID(String locationName) {
		if (locations.containsKey(locationName)) {
			return locations.get(locationName);
		} else {
			return -1;
		}
	}

	public int deleteDevice(long locationID, String deviceID) throws Exception {
		int result = -1;
		if (isConnected) {
			String url = " ";
			url = chapiURL + "api/locations/" + locationID + "/Devices/" + deviceID;
			try {
				result = doDeleteRequest(url).getResponseCode();
			} catch (IOException e) {
				throw new Exception(e.getMessage());
			}

		}
		return result;
	}

	public int setBaseStationMode(long locationID, String  deviceID, String modeToSet) {
		int result = -1;
		try {
			if (isConnected) {
				String url = " ";
				String headerData = " ";
				//api/v3/locations/{0}/devices/{1}/partitions/{2}/Disarm
				if (modeToSet.toUpperCase().contains("HOME")) {
					url = chapiURL + "api/v3/locations/" + locationID+"/devices/"+deviceID+"/partitions/1/Disarm";
				}
				try {
					result = doPutRequest(url, headerData).getResponseCode();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {

		}
		return result;
	}
	public int setUserHomeOrAway(long locationID, long geofenceID, long userID, boolean setUserHome) {
		int result = -1;
		try {
			if (isConnected) {
				String url = " ";
				String headerData = " ";

				url = chapiURL + "api/locations/" + locationID + "/GeoFence/" + geofenceID + "/GangMember/" + userID
						+ "/GeoFenceEvent";
				if (setUserHome) {
					headerData = "{\"type\":\"UserArrived\"}";
				} else {
					headerData = "{\"type\":\"UserDeparted\"}";
				}
				try {
					result = doPostRequest(url, headerData).getResponseCode();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {

		}
		return result;
	}
}
