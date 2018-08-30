package com.honeywell.keywords.lyric.das;

import java.util.HashMap;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

public class VerifyTimerIsDisplayed extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	public boolean flag = true;

	public VerifyTimerIsDisplayed(TestCases testCase, TestCaseInputs inputs) {
		// this.inputs = inputs;
		this.testCase = testCase;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user should be displayed with a switching timer$")
	public boolean keywordSteps() {
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DASSecurityCard");
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "TimerProgressBar", 10)) {
			Keyword.ReportStep_Pass(testCase, "Timer Progress Bar is displayed");
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Timer Progress Bar is not displayed");
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
