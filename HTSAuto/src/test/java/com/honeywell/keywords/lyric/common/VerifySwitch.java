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

public class VerifySwitch extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	public ArrayList<String> expectedLocator;
	public boolean flag = true;
	private HashMap<String, MobileObject> fieldObjects;
	private String currentValue;
	public VerifySwitch(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedLocator) {
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
	@KeywordStep(gherkins = "^verify \"(.+)\" button is \"(.+)\"$")
	public boolean keywordSteps() throws KeywordException {
		if (testCase.isTestSuccessful()) {
			switch (expectedLocator.get(0).toUpperCase()) {
			case "HOMECAMERA": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				if(testCase.getPlatform().toUpperCase().contains("IOS")){
					currentValue=MobileUtils.getFieldValue(fieldObjects, testCase, "CameraState");
				}
				else{
					currentValue=MobileUtils.getMobElements(fieldObjects, testCase, "CameraState").get(1).getText();
					Keyword.ReportStep_Pass(testCase, "Camera is in "+currentValue);
				}
				switch (expectedLocator.get(1).toUpperCase()) {
				case "ENABLED": {
					Keyword.ReportStep_Pass(testCase, "Camera is in "+currentValue);
					if(currentValue.equalsIgnoreCase("ON")||currentValue.equalsIgnoreCase("1")){
						Keyword.ReportStep_Pass(testCase, "Camera is in enabled state");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
								expectedLocator.get(0) + " is not in enabled state");
					}
					break;
				}
				case "DISABLED": {
					if(currentValue.equalsIgnoreCase("OFF")||currentValue.equalsIgnoreCase("0")){
						Keyword.ReportStep_Pass(testCase, "Camera is in disabled state");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
								expectedLocator.get(0) + " is not in disabled state");
					}
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

			
			case "NIGHTCAMERA": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				if(testCase.getPlatform().toUpperCase().contains("IOS")){
					currentValue=MobileUtils.getFieldValue(fieldObjects, testCase, "CameraState");
				}
				else{
					currentValue=MobileUtils.getMobElements(fieldObjects, testCase, "CameraState").get(1).getText();
					Keyword.ReportStep_Pass(testCase, "Camera is in "+currentValue);
				}
				switch (expectedLocator.get(1).toUpperCase()) {
				case "ENABLED": {
					Keyword.ReportStep_Pass(testCase, "Camera is in "+currentValue);
					if(currentValue.equalsIgnoreCase("ON")||currentValue.equalsIgnoreCase("1")){
						Keyword.ReportStep_Pass(testCase, "Camera is in enabled state");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
								expectedLocator.get(0) + " is not in enabled state");
					}
					break;
				}
				case "DISABLED": {
					if(currentValue.equalsIgnoreCase("OFF")||currentValue.equalsIgnoreCase("0")){
						Keyword.ReportStep_Pass(testCase, "Camera is in disabled state");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
								expectedLocator.get(0) + " is not in disabled state");
					}
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
