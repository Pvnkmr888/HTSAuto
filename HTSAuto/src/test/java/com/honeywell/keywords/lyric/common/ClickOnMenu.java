package com.honeywell.keywords.lyric.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.Dimension;

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

public class ClickOnMenu extends Keyword {

	private TestCases testCase;
	private TestCaseInputs inputs;
	public ArrayList<String> expectedLocator;
	public boolean flag = true;
	private HashMap<String, MobileObject> fieldObjects;
	public static final String CURRENTDELAYVALUE="CURRENTDELAYVALUE";

	public ClickOnMenu(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedLocator) {
		this.testCase = testCase;
		this.inputs = inputs;
		this.expectedLocator = expectedLocator;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() throws KeywordException {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user clicks on (.*) menu$")
	public boolean keywordSteps() throws KeywordException {
		if (testCase.isTestSuccessful()) {
			switch (expectedLocator.get(0).toUpperCase()) {
			case "DAS DEVICE": {
				String base_station = inputs.getInputValue("LOCATION1_DEVICE1_NAME").toString();
				if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
					flag = flag & MobileUtils.clickOnElement(testCase, "xpath", "//*[@text='"+base_station+"']");
				}
				else{
					flag = flag & MobileUtils.clickOnElement(testCase, "name", base_station);
				}
				break;
			}
			case "HOME SETTINGS":{
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				MobileUtils.isMobElementExists(fieldObjects, testCase, "HomeSettings");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "HomeSettings");
				break;
			}
			case "AWAY SETTINGS":{
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "AwaySettings");
				break;
			}
			case "NIGHT SETTINGS":{
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "SleepSettings");
				break;
			}
			case "BASE STATION SETTINGS": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				//testCase.getMobileDriver().scrollToExact("Base Station");
				Dimension dimensions = testCase.getMobileDriver().manage().window().getSize();
				int startx = (dimensions.width * 50) / 100;
				int starty = (dimensions.height * 90) / 100;
				int endx = (dimensions.width * 50) / 100;
				int endy = (dimensions.height * 35) / 100;
				try{
					testCase.getMobileDriver().swipe(endx, endy,startx, starty,  1000);
					Keyword.ReportStep_Pass(testCase,"swipe up to focus on Base station menu was successfull");
				}catch(Exception e){
					flag=false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,"Failed to scroll");
				}
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "BaseStation_Option");
				break;
			}
			case "EXIT ENTRY DELAY":{
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				inputs.setInputValue(CURRENTDELAYVALUE,MobileUtils.getFieldValue(fieldObjects, testCase, "CurrentDelayValue"));
				Keyword.ReportStep_Pass(testCase, "value is "+inputs.getInputValue(CURRENTDELAYVALUE));
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "DelaySettings");
				break;
			}
			case "CAMERA SETTINGS": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "Base_StationCamera");
				break;
			}
			case "NIGHT VISION SETTINGS":{
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "NightVision_Option");
				break;
			}
			case "VIDEO QUALITY SETTINGS":{
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "VideoQuality_Option");
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
