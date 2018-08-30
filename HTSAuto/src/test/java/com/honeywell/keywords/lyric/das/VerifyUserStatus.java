package com.honeywell.keywords.lyric.das;

import java.util.ArrayList;
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
import com.honeywell.lyric.utils.das.DASCommandControlUtils;

public class VerifyUserStatus extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	private ArrayList<String> status;
	public boolean flag = true;

	public VerifyUserStatus(TestCases testCase, TestCaseInputs inputs, ArrayList<String> status) {
		// this.inputs = inputs;
		this.testCase = testCase;
		this.status = status;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		flag = flag & DASCommandControlUtils.waitForProgressBarToComplete(testCase);
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user status should be set to (.*)$")
	public boolean keywordSteps() {
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DASSecurityCard");
		String currentStatus = MobileUtils.getMobElement(fieldObjects, testCase, "StatusOptionsPanel")
				.getAttribute("name");
		if(currentStatus.equals("Sleep"))
		{
			currentStatus="Night";
		}
		if(currentStatus.equalsIgnoreCase(status.get(0)))
		{
			Keyword.ReportStep_Pass(testCase, "User status is set to " + status.get(0));
		}
		else
		{
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "User status is not set to " + status.get(0));
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
