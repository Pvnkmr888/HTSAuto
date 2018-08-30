package com.honeywell.keywords.lyric.common;

import java.util.ArrayList;
import java.util.HashMap;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordException;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

public class ClickOnButton extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	public ArrayList<String> expectedLocator;
	public boolean flag = true;
	private HashMap<String, MobileObject> fieldObjects;

	public ClickOnButton(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedLocator) {
		this.testCase = testCase;
		// this.inputs = inputs;
		this.expectedLocator = expectedLocator;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() throws KeywordException {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user clicks on (.*) button$")
	public boolean keywordSteps() throws KeywordException {
		if (testCase.isTestSuccessful()) {
			switch (expectedLocator.get(0).toUpperCase()) {
			
			case "ADD DEVICE": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "HomeScreen");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "AddNewDeviceIcon");
				break;
			}
			case "HOME":
			{
				String locatorType,locatorValue;
				if(testCase.getPlatform().toUpperCase().contains("ANDROID"))
				{
					locatorType = "xpath";
					locatorValue = "//*[@text='Home']";
				}
				else
				{
					locatorType = "name";
					locatorValue = "Home";
				}
				flag = flag & MobileUtils.clickOnElement(testCase, locatorType, locatorValue);
				break;
			}
			
			case "FRONT HALL DOOR":
			{
				String locatorType,locatorValue;
				if(testCase.getPlatform().toUpperCase().contains("ANDROID"))
				{
					locatorType = "xpath";
					locatorValue = "//*[@text='Front Hall Door']";
				}
				else
				{
					locatorType = "name";
					locatorValue = "Front Hall Door";
				}
				flag = flag & MobileUtils.clickOnElement(testCase, locatorType, locatorValue);
				break;
			}
			case "MY APOLLO":
			{
				String locatorType,locatorValue;
				if(testCase.getPlatform().toUpperCase().contains("ANDROID"))
				{
					locatorType = "xpath";
					locatorValue = "//*[@text='My Apollo']";
				}
				else
				{
					locatorType = "name";
					locatorValue = "My Apollo";
				}
				flag = flag & MobileUtils.clickOnElement(testCase, locatorType, locatorValue);
				break;
			}
			case "CLOSE":
			{
				String locatorType,locatorValue;
				if(testCase.getPlatform().toUpperCase().contains("ANDROID"))
				{
					locatorType = "xpath";
					locatorValue = "//*[@text='Close']";
				}
				else
				{
					locatorType = "name";
					locatorValue = "Close";
				}
				flag = flag & MobileUtils.clickOnElement(testCase, locatorType, locatorValue);
				break;
			}
			case "JOIN":
			{
				String locatorType,locatorValue;
				if(testCase.getPlatform().toUpperCase().contains("ANDROID"))
				{
					locatorType = "xpath";
					locatorValue = "//*[@text='Join']";
				}
				else
				{
					locatorType = "name";
					locatorValue = "Join";
				}
				flag = flag & MobileUtils.clickOnElement(testCase, locatorType, locatorValue);
				break;
			}
			case "NEXT": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "NextButton");
				break;
			}
			case "NO": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "NoButton");
				break;
			}
			
			
			case "CANCEL": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
				flag = flag
						& MobileUtils.clickOnElement(fieldObjects, testCase, "InstallationScreen_NavigationBarCancel");
				break;
			}
			case "BACK": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
				flag = flag
						& MobileUtils.clickOnElement(fieldObjects, testCase, "InstallationScreen_NavigationBarBack");
				break;
			}
			
			case "SET UP SENSORS": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "SetUpSensorsButton");
				break;
			}
			case "DOOR": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "DoorButton");
				break;
			}
			case "HAMBURGER MENU": {
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fieldObjects = MobileUtils.loadObjectFile(testCase, "GlobalDrawer");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "GlobalDrawerButton");
				break;
			}
			default: {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						expectedLocator.get(0) + " input not handled");
			}
			}
		} else {
			flag = false;
			Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
					"Scenario steps failed already, hence skipping the verification");
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() throws KeywordException {
		return flag;
	}
}
