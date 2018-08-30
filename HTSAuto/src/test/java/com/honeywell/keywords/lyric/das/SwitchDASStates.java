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
import com.honeywell.lyric.utils.das.DASCommandControlUtils;

public class SwitchDASStates extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	private ArrayList<String> states;
	public boolean flag = true;

	public SwitchDASStates(TestCases testCase, TestCaseInputs inputs, ArrayList<String> states) {
		// this.inputs = inputs;
		this.testCase = testCase;
		this.states = states;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user switches from (.*) to (.*)$")
	public boolean keywordSteps() {
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DASSecurityCard");
		String currentStatus = MobileUtils.getMobElement(fieldObjects, testCase, "StatusOptionsPanel")
				.getAttribute("name");
		if (states.get(0).equalsIgnoreCase("Home")) {
			if (currentStatus.equalsIgnoreCase("Home")) {
				Keyword.ReportStep_Pass(testCase, "User status is already set to Home");
			} else {
				flag = flag & DASCommandControlUtils.changeStatus(testCase, "Home");
				flag = flag & DASCommandControlUtils.waitForProgressBarToComplete(testCase);
			}
		} else if (states.get(0).equalsIgnoreCase("Night")) {
			if (currentStatus.equalsIgnoreCase("Sleep")) {
				Keyword.ReportStep_Pass(testCase, "User status is already set to Night");
			} else {
				flag = flag & DASCommandControlUtils.changeStatus(testCase, "Night");
				flag = flag & DASCommandControlUtils.waitForTimerToComplete(testCase);
				flag = flag & DASCommandControlUtils.waitForProgressBarToComplete(testCase);
			}
		} else if (states.get(0).equalsIgnoreCase("Away")) {
			if (currentStatus.equalsIgnoreCase("Away")) {
				Keyword.ReportStep_Pass(testCase, "User status is already set to Away");
			} else {
				flag = flag & DASCommandControlUtils.changeStatus(testCase, "Away");
				flag = flag & DASCommandControlUtils.waitForTimerToComplete(testCase);
				flag = flag & DASCommandControlUtils.waitForProgressBarToComplete(testCase);
			}
		}
		if(!flag)
		{
			return flag;
		}
		if (states.get(1).equalsIgnoreCase("Home")) {
			flag = flag & DASCommandControlUtils.changeStatus(testCase, "Home");
			//flag = flag & DASCommandControlUtils.waitForTimerToComplete(testCase);
		} else if (states.get(1).equalsIgnoreCase("Night")) {
			flag = flag & DASCommandControlUtils.changeStatus(testCase, "Night");
			//flag = flag & DASCommandControlUtils.waitForTimerToComplete(testCase);
		} else if (states.get(1).equalsIgnoreCase("Away")) {
			flag = flag & DASCommandControlUtils.changeStatus(testCase, "Away");
			//flag = flag & DASCommandControlUtils.waitForTimerToComplete(testCase);
		}

		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
