package com.honeywell.lyric.utils.das;

import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

import java.util.HashMap;

public class DAS_SettingsUtils {
	public static boolean HomeSettingsScreen(TestCases testCase) {
		boolean flag = true;
		try {
			HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_Settings");
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "HomeSettingsIcon", 5)) {
				Keyword.ReportStep_Pass(testCase, "HomeSettingsIcon is displayed on the screen");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Could not find HomeSettingsIcon in the screen");
			}
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "HomeSettingsDescription", 5)) {
				Keyword.ReportStep_Pass(testCase, "HomeSettingsDescription is displayed on the screen");
				/*if(MobileUtils.getFieldValue(fieldObjects, testCase, "HomeSettingsDescription")
						.equalsIgnoreCase("Use Home mode while you or someone is at home. "
								+ "In Home mode, DAS will not trigger any alarm,"
								+ " but you will hear chimes when you open/close the doors or windows.")){
					Keyword.ReportStep_Pass(testCase,MobileUtils.getFieldValue(fieldObjects, testCase, "HomeSettingsDescription")+ " is displayed on the screen");
				}else{
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Could not match HomeSettings description in the screen");
				}*/
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Could not find HomeSettingsDescription in the screen");
			}
		} catch (Exception e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Could not scroll to Lyric DAS text");
		}
		return flag;
	}

	public static boolean AwaySettingsScreen(TestCases testCase) {
		boolean flag = true;
		try {
			HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_Settings");
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "AwaySettingsIcon", 5)) {
				Keyword.ReportStep_Pass(testCase, "AwaySettingsIcon is displayed on the screen");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Could not find AwaySettingsIcon in the screen");
			}
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "AwaySettingsDescription", 5)) {
				Keyword.ReportStep_Pass(testCase, "AwaySettingsDescription is displayed on the screen");
				/*if(MobileUtils.getFieldValue(fieldObjects, testCase, "AwaySettingsDescription")
						.equalsIgnoreCase("Use Away mode while you or someone is at Away. "
								+ "In Away mode, DAS will not trigger any alarm,"
								+ " but you will hear chimes when you open/close the doors or windows.")){
					Keyword.ReportStep_Pass(testCase,MobileUtils.getFieldValue(fieldObjects, testCase, "AwaySettingsDescription")+ " is displayed on the screen");
				}else{
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Could not match AwaySettings description in the screen");
				}*/
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Could not find AwaySettingsDescription in the screen");
			}
		} catch (Exception e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Could not scroll to Lyric DAS text");
		}
		return flag;
	}

	public static boolean SleepSettingsScreen(TestCases testCase) {
		boolean flag = true;
		try {
			HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_Settings");
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "SleepSettingsIcon", 5)) {
				Keyword.ReportStep_Pass(testCase, "SleepSettingsIcon is displayed on the screen");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Could not find SleepSettingsIcon in the screen");
			}
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "SleepSettingsDescription", 5)) {
				Keyword.ReportStep_Pass(testCase, "SleepSettingsDescription is displayed on the screen");
				/*if(MobileUtils.getFieldValue(fieldObjects, testCase, "SleepSettingsDescription")
						.equalsIgnoreCase("Use Sleep mode while you or someone is at Sleep. "
								+ "In Sleep mode, DAS will not trigger any alarm,"
								+ " but you will hear chimes when you open/close the doors or windows.")){
					Keyword.ReportStep_Pass(testCase,MobileUtils.getFieldValue(fieldObjects, testCase, "SleepSettingsDescription")+ " is displayed on the screen");
				}else{
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Could not match SleepSettings description in the screen");
				}*/
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Could not find SleepSettingsDescription in the screen");
			}
		} catch (Exception e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Could not scroll to Lyric DAS text");
		}
		return flag;
	}


	public static boolean verifySensorInstallationScreen(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_NavigationBarTitle", 5, true)) {
			String toolbarTitle = MobileUtils.getFieldValue(fieldObjects, testCase,
					"InstallationScreen_NavigationBarTitle");
			String textToBeVerified;
			if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
				textToBeVerified = "Add Sensors";
			} else {
				textToBeVerified = "Add Device";
			}
			if (toolbarTitle.equals(textToBeVerified)) {
				Keyword.ReportStep_Pass(testCase, "'" + textToBeVerified + "' title is correctly displayed");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"'" + textToBeVerified + "' title is not correctly displayed. Displayed : " + toolbarTitle
						+ ". Expected : " + textToBeVerified);
			}
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"'Add Sensors' title is not displayed on the toolbar");
		}

		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "SensorInstallationDescription", 5, true)) {
			String header = MobileUtils.getFieldValue(fieldObjects, testCase, "SensorInstallationDescription");
			String textToBeVerified;
			if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
				textToBeVerified = "Activate your sensors to pair";
			} else {
				textToBeVerified = "Activate your devices to pair";
			}

			if (header.equals(textToBeVerified)) {
				Keyword.ReportStep_Pass(testCase, "'" + textToBeVerified + "' header is correctly displayed");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"'" + textToBeVerified + "' header is not correctly displayed. Displayed : " + header
						+ ". Expected : " + textToBeVerified);
			}
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"'Activate your sensors to pair' header is not displayed");
		}
		return flag;
	}

	public static void verifyPlacementScreen(TestCases testCase) {
		HashMap<String, MobileObject> fieldObjects;
		fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_NavigationBarBack", 5)) {
			Keyword.ReportStep_Pass(testCase,
					MobileUtils.getFieldValue(fieldObjects, testCase, "InstallationScreen_NavigationBarBack")
					+ " is displayed");
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
					"InstallationScreen_NavigationBarBack is not displayed");
		}
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_NavigationBarTitle", 5)) {
			Keyword.ReportStep_Pass(testCase,
					MobileUtils.getFieldValue(fieldObjects, testCase, "InstallationScreen_NavigationBarTitle")
					+ " is displayed");
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
					"InstallationScreen_NavigationBarTitle is not displayed");
		}
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_NavigationBarCancel", 5)) {
			Keyword.ReportStep_Pass(testCase,
					MobileUtils.getFieldValue(fieldObjects, testCase, "InstallationScreen_NavigationBarCancel")
					+ " is displayed");
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
					"InstallationScreen_NavigationBarCancel is not displayed");
		}
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_PlacementHeader", 5)) {
			Keyword.ReportStep_Pass(testCase,
					MobileUtils.getFieldValue(fieldObjects, testCase, "InstallationScreen_PlacementHeader")
					+ " is displayed");
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
					"InstallationScreen_PlacementHeader is not displayed");
		}
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_PlacementDescription", 5)) {
			Keyword.ReportStep_Pass(testCase,
					MobileUtils.getFieldValue(fieldObjects, testCase, "InstallationScreen_PlacementDescription")
					+ " is displayed");
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
					"InstallationScreen_PlacementDescription is not displayed");
		}
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_PlacementImage", 5)) {
			Keyword.ReportStep_Pass(testCase, "PlacementImage is displayed");
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
					"InstallationScreen_PlacementImage is not displayed");
		}
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_NextButton", 5)) {
			Keyword.ReportStep_Pass(testCase,
					MobileUtils.getFieldValue(fieldObjects, testCase, "InstallationScreen_NextButton")
					+ " is displayed");
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
					"InstallationScreen_NextButton is not displayed");
		}
	}

	public static boolean verifyPowerUpScreen(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_NavigationBarBack", 5)) {
			Keyword.ReportStep_Pass(testCase, "Back button is displayed");
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "Back Button is not displayed");
		}

		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_NavigationBarTitle", 5)) {
			String toolbarTitle = MobileUtils.getFieldValue(fieldObjects, testCase,
					"InstallationScreen_NavigationBarTitle");
			if (toolbarTitle.equals("Installation")) {
				Keyword.ReportStep_Pass(testCase, "Installation title is displayed on the toolbar");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
						"Installation title is not displayed on the toolbar");
			}
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "Toolbar title is not displayed");
		}

		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_PowerUpHeader", 5)) {
			String headerText = MobileUtils.getFieldValue(fieldObjects, testCase,
					"InstallationScreen_PowerUpHeader");
			String textToBeVerified;
			if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
				textToBeVerified = "Power Up Lyric DAS";
			} else {
				textToBeVerified = "Power up Lyric DAS";
			}
			if (headerText.equals(textToBeVerified)) {
				Keyword.ReportStep_Pass(testCase, "Power Up Lyric DAS header is displayed correctly");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
						"Power Up Lyric DAS header is not displayed correctly. Expected : " + textToBeVerified
						+ ". Actual : " + headerText);
			}

		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "Power Up Lyric DAS header is not displayed");
		}

		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_PowerUpDescription", 5)) {
			String headerDescText = MobileUtils.getFieldValue(fieldObjects, testCase,
					"InstallationScreen_PowerUpDescription");
			String textToBeVerified;
			if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
				textToBeVerified = "Plug in your Lyric DAS. Wait until the base begins flashing blue (this will take about a few second), then tap NEXT to continue";
			} else {
				textToBeVerified = "Plug in your Lyric DAS. Wait until the base begins flashing blue (this will take about a few seconds). Then tap NEXT to continue";
			}
			if (headerDescText.equals(textToBeVerified)) {
				Keyword.ReportStep_Pass(testCase, "Power Up Lyric DAS header description is displayed correctly");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
						"Power Up Lyric DAS header description is not displayed correctly. Expected : " + textToBeVerified
						+ ". Actual : " + headerDescText);
			}
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE,
					"Power Up Lyric DAS header description is not displayed");
		}

		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_PowerUpImage", 5)) {
			Keyword.ReportStep_Pass(testCase, "Power Up Image is displayed");
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "Power Up Image is not displayed");
		}
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "InstallationScreen_NextButton", 5)) {
			Keyword.ReportStep_Pass(testCase, "Next Button is displayed");
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "Next Button is not displayed");
		}
		return flag;
	}
}
