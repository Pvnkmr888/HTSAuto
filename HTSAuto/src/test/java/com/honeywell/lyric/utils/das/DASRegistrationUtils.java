package com.honeywell.lyric.utils.das;

import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

import java.util.HashMap;

public class DASRegistrationUtils {

	public static boolean verifyQuickStartGuidePopUp(TestCases testCase) {
		boolean flag = true;
		String locatorType, locatorValue;
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			locatorType = "xpath";
			locatorValue = "//android.widget.TextView[@text='Use the Quick Start Guide']";
		} else {
			locatorType = "name";
			locatorValue = "Use Quick Start Guide";
		}
		if (MobileUtils.isMobElementExists(locatorType, locatorValue, testCase, 5, false)) {
			Keyword.ReportStep_Pass(testCase, "Use Quick Start Guide Pop Up is displayed");
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Use Quick Start Guide Pop up is not displayed");
		}
		return flag;
	}

	public static boolean verifyPowerBaseStationInstructionScreenVisible(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("text");
				if (displayedText.equals("Power 'Base Station'")) {
					Keyword.ReportStep_Pass(testCase, "Power 'Base Station' text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Power 'Base Station' text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : DAS Name");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Power 'Base Station' text is not displayed on the toolbar");
			}

			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "HeaderDescription", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "HeaderDescription")
						.getAttribute("text");
				if (displayedText.equals(
						"1. Plug your Base Station into the wall.\n2. Wait for your Base Station to flash blue. Keep in mind that this may take up to 90 seconds.\n3. Tap NEXT to continue")) {
					Keyword.ReportStep_Pass(testCase, "Power Base Station Instructions displayed correctly");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Power Base Station Instructions not correctly displayed. Displayed : " + displayedText
									+ ". Expected : 1. Plug your Base Station into the wall.\n2. Wait for your Base Station to flash blue. Keep in mind that this may take up to 30 seconds.\n3. Tap NEXT to continue");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Power Base Station Instructions  is not displayed");
			}
		} else {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("value");
				if (displayedText.equals("Power Base Station")) {
					Keyword.ReportStep_Pass(testCase, "Power Base Station text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Power Base Station text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Power Base Station");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Power Base Station text is not displayed on the toolbar");
			}
			if (MobileUtils.isMobElementExists("name",
					"1. Plug your Base Station into the wall 2. Wait for your Base Station to flash blue.  Keep in mind that this may take up to 60 seconds.  3. Tap Next to continue",
					testCase, 5,false)) {
				Keyword.ReportStep_Pass(testCase, "Power Base Station Instructions displayed correctly");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Power Base Station Instructions not displayed correctly");
			}
		}
		return flag;
	}

	public static boolean verifyConnectToNetworkScreenVisible(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 60,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("text");
				if (displayedText.equals("Connect to Network")) {
					Keyword.ReportStep_Pass(testCase, "Connect to Network text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Connect to Network text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Connect to Network");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Connect to Network text is not displayed on the toolbar");
			}

			/*if (MobileUtils.isMobElementExists(fieldObjects, testCase, "HeaderDescription", 5)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "HeaderDescription")
						.getAttribute("text");
				if (displayedText.equals("Select your wi-fi network.")) {
					Keyword.ReportStep_Pass(testCase, "Select your wi-fi network. text is displayed correctly");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Select your wi-fi network. text is not correctly displayed. Displayed : " + displayedText
									+ ". Expected : Select your wi-fi network.");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Select your wi-fi network. text is not displayed");
			}*/
		} else {
			if (MobileUtils.isMobElementExists("name", "Select your Wi Fi network", testCase, 60,false)) {
				Keyword.ReportStep_Pass(testCase, "Select your Wi Fi network text displayed correctly");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Select your Wi Fi network text not displayed");
			}

		}

		return flag;
	}

	public static boolean verifySetupCompleteScreenVisible(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("text");
				if (displayedText.equals("Base station installed")) {
					Keyword.ReportStep_Pass(testCase, "Base station installed text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Base station installed text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Base station installed");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Base station installed text is not displayed on the toolbar");
			}

			/*if (MobileUtils.isMobElementExists(fieldObjects, testCase, "HeaderSubtitle", 5)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "HeaderSubtitle")
						.getAttribute("text");
				if (displayedText.equals("Apollo base station setup is complete!")) {
					Keyword.ReportStep_Pass(testCase,
							"Apollo base station setup is complete! text is displayed correctly");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Apollo base station setup is complete! text is not correctly displayed. Displayed : "
									+ displayedText + ". Expected : Apollo base station setup is complete!");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Apollo base station setup is complete! text is not displayed");
			}*/
		} else {
			if (MobileUtils.isMobElementExists("name", "Your Base Station is set up.", testCase, 5,false)) {
				Keyword.ReportStep_Pass(testCase, "Your Base Station is set up. text is displayed correctly");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Your Base Station is set up. text is not displayed");
			}
		}
		return flag;
	}

	public static boolean verifyPowerBaseStationScreenVisible(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("text");
				if (displayedText.equals("Power 'Base Station'")) {
					Keyword.ReportStep_Pass(testCase, "Power 'Base Station' text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Power 'Base Station' text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Power 'Base Station'");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Power 'Base Station' text is not displayed on the toolbar");
			}

			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "HeaderDescription", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "HeaderDescription")
						.getAttribute("text");
				if (displayedText.equals("Connect the Power Cable to the bottom of your Base Station.")) {
					Keyword.ReportStep_Pass(testCase,
							"'Connect the Power Cable to the bottom of your Base Station.' text is displayed correctly");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"'Connect the Power Cable to the bottom of your Base Station.' text is not correctly displayed. Displayed : "
									+ displayedText
									+ ". Expected : Connect the Power Cable to the bottom of your Base Station.");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"'Connect the Power Cable to the bottom of your Base Station.' text is not displayed");
			}
		} else {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("value");
				if (displayedText.equals("Power Base Station")) {
					Keyword.ReportStep_Pass(testCase, "Power Base Station text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Power Base Station text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Power Base Station");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Power Base Station text is not displayed on the toolbar");
			}
			if (MobileUtils.isMobElementExists("name", "Connect the Power Cable to the bottom of your Base Station.",
					testCase, 5,false)) {
				Keyword.ReportStep_Pass(testCase,
						"Connect the Power Cable to the bottom of your Base Station. text displayed correctly");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Connect the Power Cable to the bottom of your Base Station. text not displayed correctly");
			}
		}
		return flag;
	}

	public static boolean verifyDASNameScreenVisible(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("text");
				if (displayedText.equals("Name Base Station")) {
					Keyword.ReportStep_Pass(testCase, "Name Base Station text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Name Base Station text is not correctly displayed on the toolbar. Displayed : " + displayedText
									+ ". Expected : Name Base Station");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Name Base Station text is not displayed on the toolbar");
			}

			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "HeaderDescription", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "HeaderDescription")
						.getAttribute("text");
				if (displayedText.equals("Select a suggested name for your Base Station")) {
					Keyword.ReportStep_Pass(testCase, "Select a suggested name for your Base Station text is displayed correctly");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Select a suggested name for your Base Station text is not correctly displayed. Displayed : " + displayedText
									+ ". Expected : Select a suggested name for your Base Station");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Select a suggested name for your Base Station text is not displayed");
			}
		} else {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("value");
				if (displayedText.equals("Name Your Base Station")) {
					Keyword.ReportStep_Pass(testCase, "Name Your Base Station text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Name Your Base Station text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Name Your Base Station");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Name Your Base Station text is not displayed on the toolbar");
			}

			if (MobileUtils.isMobElementExists("name", "Select a suggested name for your Base Station", testCase, 5,false)) {
				Keyword.ReportStep_Pass(testCase,
						"Select a suggested name for your Base Station text is displayed correctly");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Select a suggested name for your Base Station text is not displayed");
			}

		}
		return flag;
	}

	public static boolean verifySelectBaseStationScreenVisible(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 120, false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle", false)
						.getAttribute("text");
				if (displayedText.equals("Select Base Station")) {
					Keyword.ReportStep_Pass(testCase, "Select Base Station text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Select Base Station text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Select Base Station");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Select Base Station text is not displayed on the toolbar");
			}

			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "HeaderSubtitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "HeaderSubtitle")
						.getAttribute("text");
				if (displayedText.equals("Multiple Base Station found")) {
					Keyword.ReportStep_Pass(testCase, "'Multiple Base Station found' text is displayed correctly");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"'Multiple Base Station found' text is not correctly displayed. Displayed : "
									+ displayedText + ". Expected : Multiple Base Station found");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"'Multiple Base Station found' text is not displayed");
			}
		} else {
			if (MobileUtils.isMobElementExists("xpath", "//XCUIElementTypeStaticText[@value='Installation']", testCase,
					120, false)) {
				Keyword.ReportStep_Pass(testCase, "Installation text is displayed on the toolbar");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Installation text is not displayed on the toolbar");
			}
		}
		return flag;
	}

	public static boolean verifyLocationSelectionScreenVisible(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("text");
				if (displayedText.equals("Device Home")) {
					Keyword.ReportStep_Pass(testCase, "Device Home text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Device Home text is not correctly displayed on the toolbar. Displayed : " + displayedText
									+ ". Expected : Device Home");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Device Home text is not displayed on the toolbar");
			}

			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "HeaderSubtitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "HeaderSubtitle")
						.getAttribute("text");
				if (displayedText.equals("Account confirmed. Name your home")) {
					Keyword.ReportStep_Pass(testCase,
							"'Account confirmed. Name your home' text is displayed correctly");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"'Account confirmed. Name your home' text is not correctly displayed. Displayed : "
									+ displayedText + ". Expected : Account confirmed. Name your home");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"'Account confirmed. Name your home' text is not displayed");
			}
		} else {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("value");
				if (displayedText.equals("Base Station Home")) {
					Keyword.ReportStep_Pass(testCase, "Base Station Home text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Base Station Home text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Base Station Home");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Base Station Home text is not displayed on the toolbar");
			}
			if (MobileUtils.isMobElementExists("name", "Account Created", testCase, 5,false)) {
				Keyword.ReportStep_Pass(testCase, "'Account Created' text is displayed correctly");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"'Account Created' text is not displayed");
			}
		}
		return flag;
	}

	public static boolean verifySelectDeviceScreenVisible(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("text");
				if (displayedText.equals("Add New Device")) {
					Keyword.ReportStep_Pass(testCase, "Add New Device text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Add New Device text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Add New Device");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Add New Device text is not displayed on the toolbar");
			}

			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "SelectDeviceScreenHeader", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "SelectDeviceScreenHeader")
						.getAttribute("text");
				if (displayedText.equals("Select a device to install")) {
					Keyword.ReportStep_Pass(testCase, "Select a device to install text is displayed correctly");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Select a device to install text is not correctly displayed. Displayed : " + displayedText
									+ ". Expected : Select a device to install");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Select a device to install text is not displayed");
			}
		} else {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 5,false)) {
				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("value");
				if (displayedText.equals("Add New Device")) {
					Keyword.ReportStep_Pass(testCase, "Add New Device text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Add New Device text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Add New Device");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Add New Device text is not displayed on the toolbar");
			}

			if (MobileUtils.isMobElementExists("name", "Select a device to install", testCase, 5,false)) {
				Keyword.ReportStep_Pass(testCase, "'Select a device to install' is displayed");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"'Select a device to install' is not displayed");
			}
		}
		return flag;
	}

	public static boolean verifyRegisterBaseStationScreenVisible(TestCases testCase) {
		boolean flag = true;
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ToolbarTitle", 120,false)) {

				String displayedText = MobileUtils.getMobElement(fieldObjects, testCase, "ToolbarTitle")
						.getAttribute("text");
				if (displayedText.equals("Register 'Base Station'")) {
					Keyword.ReportStep_Pass(testCase, "Register 'Base Station' text is displayed on the toolbar");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Register 'Base Station' text is not correctly displayed on the toolbar. Displayed : "
									+ displayedText + ". Expected : Add New Device");
				}
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Register 'Base Station' text is not displayed on the toolbar");
			}
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "QRCodeIcon", 5,false)) {
				Keyword.ReportStep_Pass(testCase, "QR Code is displayed");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "QR Code is not displayed");
			}
		} else {
			if (MobileUtils.isMobElementExists("name",
					"Scan this code by showing it to your Base Station\u2019s camera.", testCase, 120, false)) {
				Keyword.ReportStep_Pass(testCase,
						"Scan this code by showing it to your Base Station\u2019s camera. text is displayed correctly");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Scan this code by showing it to your Base Station\u2019s camera. text is not displayed");
			}
		}
		return flag;
	}
}
