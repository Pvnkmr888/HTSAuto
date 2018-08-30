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

public class DescriptionValidation extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	public ArrayList<String> expectedLocator;
	public boolean flag = true;
	private HashMap<String, MobileObject> fieldObjects;

	public DescriptionValidation(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedLocator) {
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
	@KeywordStep(gherkins = "^(.*) with description$")
	public boolean keywordSteps() throws KeywordException {
		if (testCase.isTestSuccessful()) {
			switch (expectedLocator.get(0).toUpperCase()) {
			case "HOMEALARM IS ALWAYS OFF": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				flag= flag & MobileUtils.isMobElementExists(fieldObjects, testCase, "AlarmLabel");
				flag= flag & MobileUtils.clickOnElement(fieldObjects, testCase, "AlarmState");
				flag= flag & MobileUtils.isMobElementExists(fieldObjects, testCase, "Home_AlarmDescription");
				break;
			}
			case "AWAYALARM IS ALWAYS ON": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				flag= flag & MobileUtils.isMobElementExists(fieldObjects, testCase, "AlarmLabel");
				flag= flag & MobileUtils.clickOnElement(fieldObjects, testCase, "AlarmState");
				flag= flag & MobileUtils.isMobElementExists(fieldObjects, testCase, "Away_AlarmDescription");
				break;
			}
			case "AWAYCAMERA IS ALWAYS ON": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				flag= flag & MobileUtils.isMobElementExists(fieldObjects, testCase, "AlarmLabel");
				flag= flag & MobileUtils.clickOnElement(fieldObjects, testCase, "AlarmState");
				flag= flag & MobileUtils.isMobElementExists(fieldObjects, testCase, "Away_CameraDescription");
				break;
			}
			case "NIGHTALARM IS ALWAYS ON": {
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				flag= flag & MobileUtils.isMobElementExists(fieldObjects, testCase, "AlarmLabel");
				flag= flag & MobileUtils.clickOnElement(fieldObjects, testCase, "AlarmState");
				flag= flag & MobileUtils.isMobElementExists(fieldObjects, testCase, "Sleep_AlarmDescription");
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
