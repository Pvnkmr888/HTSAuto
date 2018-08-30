package com.honeywell.stat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.report.FailType;
import com.honeywell.lyric.utils.JasperUtils;

public class ThermostatInformation {

	private JSONObject statInformation;
	private String locationName;
	private String statName;
	private TestCases testCase;
	// private TestCaseInputs inputs;

	public ThermostatInformation(TestCases testCase, TestCaseInputs inputs) {
		this.testCase = testCase;
		// this.inputs=inputs;
		statInformation = JasperUtils.getStatInformation(testCase, inputs);
	}

	public void printStatJSON() {
		if (statInformation != null) {
			System.out.println(statInformation);
		} else {
			System.out.println("Stat info is null");
		}
	}

	public String getDeviceID() {
		if (statInformation != null) {
			return statInformation.getString("deviceID");
		} else {
			return " ";
		}
	}

	public String getDeviceClass() {
		if (statInformation != null) {
			return statInformation.getString("deviceClass");
		} else {
			return " ";
		}
	}

	public String getDeviceType() {
		if (statInformation != null) {
			return statInformation.getString("deviceType");
		} else {
			return " ";
		}
	}

	public HashMap<String, String> getDeviceMaxMinSetPoints() {
		HashMap<String, String> setPoints = new HashMap<String, String>();
		if (statInformation != null) {
			try {
				List<String> allowedModes = getAllowedModes();
				if (allowedModes.contains("Cool") && allowedModes.contains("Heat")) {
					String temp = String.valueOf(statInformation.getJSONObject("thermostat").get("maxHeatSetpoint"));
					if (getThermostatUnits().equals("Celsius")) {
						temp = JasperUtils.roundOffCelsiusData(testCase,
								JasperUtils.convertFromFahrenhietToCelsius(testCase, temp));
					}
					setPoints.put("MaxHeat", temp);
					temp = String.valueOf(statInformation.getJSONObject("thermostat").get("maxCoolSetpoint"));
					if (getThermostatUnits().equals("Celsius")) {
						temp = JasperUtils.roundOffCelsiusData(testCase,
								JasperUtils.convertFromFahrenhietToCelsius(testCase, temp));
					}
					setPoints.put("MaxCool", temp);
					temp = String.valueOf(statInformation.getJSONObject("thermostat").get("minHeatSetpoint"));
					if (getThermostatUnits().equals("Celsius")) {
						temp = JasperUtils.roundOffCelsiusData(testCase,
								JasperUtils.convertFromFahrenhietToCelsius(testCase, temp));
					}
					setPoints.put("MinHeat", temp);
					temp = String.valueOf(statInformation.getJSONObject("thermostat").get("minCoolSetpoint"));
					if (getThermostatUnits().equals("Celsius")) {
						temp = JasperUtils.roundOffCelsiusData(testCase,
								JasperUtils.convertFromFahrenhietToCelsius(testCase, temp));
					}
					setPoints.put("MinCool", temp);
				} else if (allowedModes.contains("Cool") && !allowedModes.contains("Heat")) {
					String temp = String.valueOf(statInformation.getJSONObject("thermostat").get("maxCoolSetpoint"));
					if (getThermostatUnits().equals("Celsius")) {
						temp = JasperUtils.roundOffCelsiusData(testCase,
								JasperUtils.convertFromFahrenhietToCelsius(testCase, temp));
					}
					setPoints.put("MaxCool", temp);
					temp = String.valueOf(statInformation.getJSONObject("thermostat").get("minCoolSetpoint"));
					if (getThermostatUnits().equals("Celsius")) {
						temp = JasperUtils.roundOffCelsiusData(testCase,
								JasperUtils.convertFromFahrenhietToCelsius(testCase, temp));
					}
					setPoints.put("MinCool", temp);
				} else if (!allowedModes.contains("Cool") && allowedModes.contains("Heat")) {
					String temp = String.valueOf(statInformation.getJSONObject("thermostat").get("maxHeatSetpoint"));
					if (getThermostatUnits().equals("Celsius")) {
						temp = JasperUtils.roundOffCelsiusData(testCase,
								JasperUtils.convertFromFahrenhietToCelsius(testCase, temp));
					}
					setPoints.put("MaxHeat", temp);
					temp = String.valueOf(statInformation.getJSONObject("thermostat").get("minHeatSetpoint"));
					if (getThermostatUnits().equals("Celsius")) {
						temp = JasperUtils.roundOffCelsiusData(testCase,
								JasperUtils.convertFromFahrenhietToCelsius(testCase, temp));
					}
					setPoints.put("MinHeat", temp);
				}
				return setPoints;
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Max set Points for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Device Max Set Points : Not Connected to CHAPI. Returning \"\" value");
			return setPoints;
		}
		return setPoints;
	}

	public String getThermoStatMode() {
		String systemMode = "";
		if (statInformation != null) {
			try {
				systemMode = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
						.getString("mode");
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Current System mode for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get System Mode : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		return systemMode;
	}

	public String getThermostatModeWhenAutoChangeOverActive() {
		String systemMode = "";
		if (statInformation != null) {
			try {
				systemMode = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
						.getString("heatCoolMode");
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Current System mode for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get System Mode : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		return systemMode;
	}

	public String getThermostatUnits() {
		String units = " ";
		if (statInformation != null) {
			try {
				units = statInformation.getJSONObject("thermostat").getString("units");
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Current System mode for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Thermostat Units : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		return units;
	}

	public String getIndoorTemperature() {
		String indoorTemp = " ";
		if (statInformation != null) {
			try {
				indoorTemp = statInformation.getJSONObject("thermostat").get("indoorTemperature").toString();
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Current System mode for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Indoor Temperature : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		if (getThermostatUnits().equals("Celsius")) {
			indoorTemp = JasperUtils.convertFromFahrenhietToCelsius(testCase, indoorTemp);
		} else if (getThermostatUnits().equals("Fahrenheit")) {
			Double temp = Double.parseDouble(indoorTemp);
			indoorTemp = String.valueOf(temp.intValue());
		}
		return indoorTemp;
	}

	public String getCurrentSetPoints() {
		String currentSetPoints = " ";
		if (statInformation != null) {
			try {
				if (getThermoStatMode().equals("Heat")) {
					currentSetPoints = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
							.get("heatSetpoint").toString();
				} else if (getThermoStatMode().equals("Cool")) {
					currentSetPoints = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
							.get("coolSetpoint").toString();
				} else if (getThermoStatMode().equals("Auto")) {
					if (getThermostatModeWhenAutoChangeOverActive().equals("Heat")) {
						currentSetPoints = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
								.get("heatSetpoint").toString();
					} else if (getThermostatModeWhenAutoChangeOverActive().equals("Cool")) {
						currentSetPoints = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
								.get("coolSetpoint").toString();
					}
				}
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Current System mode for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Current Set Points : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		if (getThermostatUnits().equals("Celsius")) {
			currentSetPoints = JasperUtils.convertFromFahrenhietToCelsius(testCase, currentSetPoints);
		} else if (getThermostatUnits().equals("Fahrenheit")) {
			Double temp = Double.parseDouble(currentSetPoints);
			currentSetPoints = String.valueOf(temp.intValue());
		}
		return currentSetPoints;
	}

	public boolean isStatOnline() {
		boolean status;
		if (statInformation != null) {
			try {
				status = statInformation.getBoolean("isAlive");
			} catch (Exception e) {
				status = false;
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Current System mode for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Is Stat Online : Not Connected to CHAPI. Returning \"\" value");
			status = false;
		}
		return status;

	}

	public List<String> getAllowedModes() {
		List<String> allowedModes = new ArrayList<String>();
		if (statInformation != null) {
			JSONArray temp = new JSONArray();
			try {
				temp = statInformation.getJSONObject("thermostat").getJSONArray("allowedModes");
				for (int i = 0; i < temp.length(); i++) {
					allowedModes.add(temp.getString(i));
				}
			} catch (Exception e) {
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Allowed Modes : Not Connected to CHAPI. Returning \"\" value");
		}
		return allowedModes;
	}

	public String getJasperDeviceType() {
		String type = "";
		if (statInformation != null) {
			try {
				String temp;
				temp = statInformation.get("allowedTimeIncrements").toString();
				if (temp.equals("10")) {
					type = "EMEA";
				} else if (temp.equals("15")) {
					type = "NA";
				}

			} catch (Exception e) {
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Jasper Device Type : Not Connected to CHAPI. Returning \"\" value");
		}
		return type;
	}

	public String getVacationStartTime() {
		String startTime = "";
		SimpleDateFormat vacationDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		if (statInformation != null) {
			try {
				startTime = statInformation.getJSONObject("vacationHold").getString("vacationStart");
				Date date = vacationDateFormat.parse(startTime);
				Calendar c1 = Calendar.getInstance();
				c1.setTime(date);
				c1.set(Calendar.SECOND, 0);
				startTime = vacationDateFormat.format(c1.getTime());
			} catch (Exception e) {
				startTime = " ";
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
			}
		}
		return startTime;
	}

	public String getVacationEndTime() {
		String endTime = "";
		SimpleDateFormat vacationDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		if (statInformation != null) {
			try {
				endTime = statInformation.getJSONObject("vacationHold").getString("vacationEnd");
				Date date = vacationDateFormat.parse(endTime);
				Calendar c1 = Calendar.getInstance();
				c1.setTime(date);
				c1.set(Calendar.SECOND, 0);
				endTime = vacationDateFormat.format(c1.getTime());
			} catch (Exception e) {
				endTime = " ";
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
			}
		}
		return endTime;
	}

	public String getThermostatSetPointsStatus() {
		String status = "";
		if (getDeviceType().equalsIgnoreCase("Jasper")) {
			if (statInformation != null) {
				status = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
						.get("thermostatSetpointStatus").toString();
				return status;
			} else {
				status = "";
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Stat Information is null");
			}
		}
		return status;
	}

	public boolean isVacationRunning() {
		boolean status;
		if (statInformation != null) {
			if (getThermostatSetPointsStatus().equalsIgnoreCase("VacationHold")) {
				status = true;
			} else {
				status = false;
			}
			return status;
		} else {
			status = false;
			Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
					"StatInformation is null");
		}
		return status;
	}

	public String getCurrentSchedulePeriod() {
		String schedulePeriod = "";
		if (statInformation != null) {
			schedulePeriod = statInformation.getJSONObject("currentSchedulePeriod").get("period").toString();
			return schedulePeriod;
		} else {
			schedulePeriod = "";
			Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
					"Stat Information is null");
		}
		return schedulePeriod;
	}

	public String getNextPeriodTime() {
		String nextPeriodTime = "";
		if (statInformation != null) {
			nextPeriodTime = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
					.get("nextPeriodTime").toString();
			return nextPeriodTime;
		} else {
			nextPeriodTime = "";
			Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
					"Stat Information is null");
		}
		return nextPeriodTime;
	}

	public String getHeatEndSetPoints() {
		String setPoints = "";
		if (statInformation != null) {
			try {
				setPoints = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
						.get("endHeatSetpoint").toString();
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get end heat set points for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Heat End Set Points : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		return setPoints;
	}

	public String getCoolEndSetPoints() {
		String setPoints = "";
		if (statInformation != null) {
			try {
				setPoints = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
						.get("endCoolSetpoint").toString();
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get end cool set points for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Cool End Set Points : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		return setPoints;
	}

	public String getHeatSetPoints() {
		String heatSetPoints = " ";
		if (statInformation != null) {
			try {

				heatSetPoints = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
						.get("heatSetpoint").toString();

			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Current System mode for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Heat Set Points : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		if (getThermostatUnits().equals("Celsius")) {
			heatSetPoints = JasperUtils.convertFromFahrenhietToCelsius(testCase, heatSetPoints);
		} else if (getThermostatUnits().equals("Fahrenheit")) {
			Double temp = Double.parseDouble(heatSetPoints);
			heatSetPoints = String.valueOf(temp.intValue());
		}
		return heatSetPoints;
	}

	public String getCoolSetPoints() {
		String coolSetPoints = " ";
		if (statInformation != null) {
			try {

				coolSetPoints = statInformation.getJSONObject("thermostat").getJSONObject("changeableValues")
						.get("coolSetpoint").toString();
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Current System mode for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Heat Set Points : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		if (getThermostatUnits().equals("Celsius")) {
			coolSetPoints = JasperUtils.convertFromFahrenhietToCelsius(testCase, coolSetPoints);
		} else if (getThermostatUnits().equals("Fahrenheit")) {
			Double temp = Double.parseDouble(coolSetPoints);
			coolSetPoints = String.valueOf(temp.intValue());
		}
		return coolSetPoints;
	}

	public String getIndoorHumidity() {
		String indoorHumidity = " ";
		if (statInformation != null) {
			try {
				indoorHumidity = statInformation.getJSONObject("thermostat").get("indoorHumidity").toString();
			} catch (Exception e) {
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Get Stat Information  : Unable to get Current System mode for Stat - " + statName
								+ " at location - " + locationName + " : Error occured - " + e.getMessage());
			}
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Get Indoor Humidity : Not Connected to CHAPI. Returning \"\" value");
			return "";
		}
		if (getThermostatUnits().equals("Celsius")) {
			indoorHumidity = JasperUtils.convertFromFahrenhietToCelsius(testCase, indoorHumidity);
		} else if (getThermostatUnits().equals("Fahrenheit")) {
			Double temp = Double.parseDouble(indoorHumidity);
			indoorHumidity = String.valueOf(temp.intValue());
		}
		return indoorHumidity;
	}

}
