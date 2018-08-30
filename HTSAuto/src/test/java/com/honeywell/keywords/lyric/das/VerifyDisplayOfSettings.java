package com.honeywell.keywords.lyric.das;

import java.util.ArrayList;
import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.report.FailType;
import com.honeywell.lyric.utils.das.DAS_SettingsUtils;

public class VerifyDisplayOfSettings extends Keyword {

	private TestCases testCase;
	// private HashMap<String, MobileObject> fieldObjects;
	public boolean flag = true;
	public ArrayList<String> expectedLocator;
	// private TestCaseInputs inputs;

	public VerifyDisplayOfSettings(TestCases testCase, TestCaseInputs inputs,
			ArrayList<String> expectedLocator) {
		super("Verify Screen Elements Display");
		this.testCase = testCase;
		// this.inputs=inputs;
		this.expectedLocator = expectedLocator;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^\"(.+)\" is displayed on settings screen$")
	public boolean keywordSteps() {
		try {
			if (testCase.isTestSuccessful()) {
				switch (expectedLocator.get(0).toUpperCase()) {
				case "HOME DESCRIPTION WITH ICON": {
					flag = flag & DAS_SettingsUtils.HomeSettingsScreen(testCase);
					break;
				}
				case "AWAY DESCRIPTION WITH ICON": {
					flag = flag & DAS_SettingsUtils.AwaySettingsScreen(testCase);
					break;
				}
				case "SLEEP DESCRIPTION WITH ICON": {
					flag = flag & DAS_SettingsUtils.SleepSettingsScreen(testCase);
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
		} catch (Exception e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error occured " + e.getMessage());
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
