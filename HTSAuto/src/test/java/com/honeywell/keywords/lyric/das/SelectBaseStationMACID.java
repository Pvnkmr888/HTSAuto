package com.honeywell.keywords.lyric.das;

import java.util.ArrayList;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

public class SelectBaseStationMACID extends Keyword {

	private TestCases testCase;
	private ArrayList<String> expectedMACID;
	// private TestCaseInputs inputs;

	public boolean flag = true;

	public SelectBaseStationMACID(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedMACID) {
		// this.inputs = inputs;
		this.testCase = testCase;
		this.expectedMACID = expectedMACID;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {

		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user selects a base station with MAC ID (.*)$")
	public boolean keywordSteps() {
		String macID = expectedMACID.get(0);
		String locatorType,locatorValue;
		if(testCase.getPlatform().toUpperCase().contains("ANDROID"))
		{
			locatorType = "xpath";
			locatorValue = "//android.widget.TextView[@text='" + macID + "']";
		}
		else
		{
			locatorType = "name";
			locatorValue = "C1_"+macID.replaceAll(":", "");
		}
		if (MobileUtils.isMobElementExists(locatorType, locatorValue, testCase, 5)) {
			flag = flag & MobileUtils.clickOnElement(testCase, locatorType, locatorValue);
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "MAC ID : " + macID + " not present");
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
