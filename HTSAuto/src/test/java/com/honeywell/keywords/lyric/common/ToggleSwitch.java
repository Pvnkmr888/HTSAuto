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

public class ToggleSwitch extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	public ArrayList<String> expectedLocator;
	public boolean flag = true;
	private HashMap<String, MobileObject> fieldObjects;
	private String currentValue;
	public ToggleSwitch(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedLocator) {
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
	@KeywordStep(gherkins = "^user \"(.+)\" on \"(.+)\"$")
	public boolean keywordSteps() throws KeywordException {
		if (testCase.isTestSuccessful()) {
			switch (expectedLocator.get(0).toUpperCase()) {
			case "ENABLE": {
				switch (expectedLocator.get(1).toUpperCase()) {
				case "HOMECAMERA": {
					
					fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
					if(testCase.getPlatform().toUpperCase().contains("IOS")){
						currentValue=MobileUtils.getFieldValue(fieldObjects, testCase, "CameraState");
					}
					else{
						currentValue=MobileUtils.getMobElements(fieldObjects, testCase, "CameraState").get(1).getText();
						Keyword.ReportStep_Pass(testCase, "Camera is in "+currentValue);
					}
					if(currentValue.equalsIgnoreCase("ON")||currentValue.equalsIgnoreCase("1")){
						Keyword.ReportStep_Pass(testCase, "Camera already enabled");
					}else{
						
						Keyword.ReportStep_Pass(testCase, "Camera state is "+currentValue);
						if(testCase.getPlatform().toUpperCase().contains("IOS")){
							MobileUtils.clickOnElement(fieldObjects, testCase, "CameraState");
						}
						else{
						MobileUtils.getMobElements(fieldObjects, testCase, "CameraState").get(1).click();
						}
						Keyword.ReportStep_Pass(testCase, "Camera is enabled");
					}
					if(testCase.getPlatform().toUpperCase().contains("IOS")){
						currentValue=MobileUtils.getFieldValue(fieldObjects, testCase, "CameraState");
					}
					else{
						currentValue=MobileUtils.getMobElements(fieldObjects, testCase, "CameraState").get(1).getText();
						Keyword.ReportStep_Pass(testCase, "Camera is in "+currentValue);
					}
					Keyword.ReportStep_Pass(testCase, "Camera value before navigating is "+currentValue);
					MobileUtils.clickOnElement(fieldObjects, testCase, "NavigateUp");
					while(MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",5)){
						Keyword.ReportStep_Pass(testCase, "waiting for the updating progress to disappear");
						MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",15);
					}
					Keyword.ReportStep_Pass(testCase, "waiting 25secs");
					MobileUtils.isMobElementExists(fieldObjects, testCase, "CameraState",25);

					break;
				}
				case "NIGHTCAMERA": {
					
					fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
					if(testCase.getPlatform().toUpperCase().contains("IOS")){
						currentValue=MobileUtils.getFieldValue(fieldObjects, testCase, "CameraState");
					}
					else{
						currentValue=MobileUtils.getMobElements(fieldObjects, testCase, "CameraState").get(1).getText();
						Keyword.ReportStep_Pass(testCase, "Camera is in "+currentValue);
					}
					if(currentValue.equalsIgnoreCase("ON")||currentValue.equalsIgnoreCase("1")){
						Keyword.ReportStep_Pass(testCase, "Camera already enabled");
					}else{
						
						Keyword.ReportStep_Pass(testCase, "Camera state is "+currentValue);
						if(testCase.getPlatform().toUpperCase().contains("IOS")){
							MobileUtils.clickOnElement(fieldObjects, testCase, "CameraState");
						}
						else{
						MobileUtils.getMobElements(fieldObjects, testCase, "CameraState").get(1).click();
						}
						Keyword.ReportStep_Pass(testCase, "Camera is enabled");
					}
					if(testCase.getPlatform().toUpperCase().contains("IOS")){
						currentValue=MobileUtils.getFieldValue(fieldObjects, testCase, "CameraState");
					}
					else{
						currentValue=MobileUtils.getMobElements(fieldObjects, testCase, "CameraState").get(1).getText();
						Keyword.ReportStep_Pass(testCase, "Camera is in "+currentValue);
					}
					Keyword.ReportStep_Pass(testCase, "Camera value before navigating is "+currentValue);
					MobileUtils.clickOnElement(fieldObjects, testCase, "NavigateUp");
					while(MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",5)){
						Keyword.ReportStep_Pass(testCase, "waiting for the updating progress to disappear");
						MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",15);
					}
					Keyword.ReportStep_Pass(testCase, "waiting 25secs");
					MobileUtils.isMobElementExists(fieldObjects, testCase, "CameraState",25);

					break;
				}

				default: {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							expectedLocator.get(0) + "2ND input not handled");
				} 
				}


				break;
			}


			default: {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						expectedLocator.get(0) + "1ST input not handled");
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
