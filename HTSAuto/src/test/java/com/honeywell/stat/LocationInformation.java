package com.honeywell.stat;

import org.json.JSONArray;
import org.json.JSONObject;

import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.report.FailType;
import com.honeywell.lyric.utils.JasperUtils;

public class LocationInformation {
	private JSONObject locationInformation;
	private TestCaseInputs inputs;

	private TestCases testCase;

	public LocationInformation(TestCases testCase, TestCaseInputs inputs) {
		this.testCase = testCase;
		this.inputs = inputs;
		locationInformation = JasperUtils.getLocationInformation(testCase, inputs);
	}

	public void printLocationJSON() {
		if (locationInformation != null) {
			System.out.println(locationInformation);
		} else {
			System.out.println("Location Information is null");
		}
	}

	public long getLocationID() {
		if (locationInformation != null) {
			return locationInformation.getLong("locationID");
		} else {
			return -1;
		}
	}

	public long getUserID()

	{
		if (locationInformation != null) {
			JSONArray users;
			long id = -1;
			try {
				users = locationInformation.getJSONArray("users");
				JSONObject tempObj = null;
				for (int i = 0; i < users.length(); i++) {
					tempObj = users.optJSONObject(i);
					if (tempObj.getString("username").equalsIgnoreCase(inputs.getInputValue("USERID"))) {
						id = tempObj.getLong("userID");
					}
				}
			} catch (Exception e) {
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
			}
			return id;
		} else {
			return -1;
		}
	}

	public double getLatitude() {
		double latitude = -1;
		if (locationInformation != null) {
			try {
				JSONArray geofenceArray = locationInformation.getJSONArray("geoFences");
				JSONObject tempObj;
				for (int i = 0; i < geofenceArray.length(); i++) {
					tempObj = geofenceArray.optJSONObject(i);
					latitude = tempObj.getDouble("latitude");
				}
			} catch (Exception e) {
				latitude = -1;
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Error Occured : " + e.getMessage());
				return latitude;
			}
		} else {
			latitude = -1;
			Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
					"Location Information is empty");
			return latitude;
		}
		return latitude;
	}

	public double getLongitude() {
		double longitude = -1;
		if (locationInformation != null) {
			try {
				JSONArray geofenceArray = locationInformation.getJSONArray("geoFences");
				JSONObject tempObj;
				for (int i = 0; i < geofenceArray.length(); i++) {
					tempObj = geofenceArray.optJSONObject(i);
					longitude = tempObj.getDouble("longitude");
				}
			} catch (Exception e) {
				longitude = -1;
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Error Occured : " + e.getMessage());
				return longitude;
			}
		} else {
			longitude = -1;
			Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
					"Location Information is empty");
			return longitude;
		}
		return longitude;
	}

	public int getDeviceCountOfLocation() {
		int deviceCount = 0;
		if (locationInformation != null) {
			try {
				deviceCount = locationInformation.getJSONArray("devices").length();
			} catch (Exception e) {
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Device Schedule Type : Not Connected to CHAPI. Returning \"\" value");
		}

		return deviceCount;
	}

	public long getGeofenceID(TestCaseInputs inputs) {
		if (locationInformation != null) {
			JSONArray geofences;
			long id = -1;
			try {
				geofences = locationInformation.getJSONArray("geoFences");
				id = geofences.getJSONObject(0).getLong("geoFenceID");
			} catch (Exception e) {
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
			}
			return id;
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Device Schedule Type : Not Connected to CHAPI. Returning \"\" value");
			return -1;
		}
	}

	public String deviceSchedulePeriod(TestCaseInputs inputs) {
		String schedulePeriod = "";
		if (locationInformation != null) {
			JSONArray devices;
			try {
				devices = locationInformation.getJSONArray("devices");
				JSONObject tempObj = null;
				for (int i = 0; i < devices.length(); i++) {
					tempObj = devices.optJSONObject(i);
					if (tempObj.getString("name").contains(inputs.getInputValue("LOCATION1_DEVICE1_NAME"))) {
						schedulePeriod = tempObj.getJSONObject("currentSchedulePeriod").getString("period");
					}
				}
			} catch (Exception e) {
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
			}
			return schedulePeriod;
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Device Schedule Type : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
	}

	public String getLocationFencingStatus(TestCaseInputs inputs) {
		String fencingStatus = "";
		if (locationInformation != null) {
			try {
				JSONArray arr = locationInformation.getJSONArray("geoFences");
				JSONObject obj = arr.getJSONObject(0);
				fencingStatus = obj.getJSONObject("geoOccupancy").get("withinFence").toString();
				if (fencingStatus.equalsIgnoreCase("0")) {
					fencingStatus = "Away";
				} else {
					fencingStatus = "Home";
				}
			} catch (Exception e) {
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Device Schedule Type : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		return fencingStatus;
	}

}
