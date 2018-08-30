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
import com.honeywell.lyric.utils.das.DASCommandControlUtils;

public class VerifySwitchingTextDisplayed extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	private ArrayList<String> expectedText;
	public boolean flag = true;

	public VerifySwitchingTextDisplayed(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedText) {
		// this.inputs = inputs;
		this.testCase = testCase;
		this.expectedText = expectedText;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user should be displayed with a (.*) text$")
	public boolean keywordSteps() {
			String displayedText = "";
			if (MobileUtils.isMobElementExists("id", "status_switch_message", testCase, 5)) {
				displayedText = MobileUtils.getMobElement(testCase, "id", "status_switch_message").getAttribute("text")
						+ " " + MobileUtils.getMobElement(testCase, "id", "text_status").getAttribute("text");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						expectedText.get(0) + " text is not displayed");
				return flag;
			}
			if (displayedText.equalsIgnoreCase(expectedText.get(0))) {
				Keyword.ReportStep_Pass(testCase, expectedText.get(0) + " text is displayed");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						expectedText.get(0) + " text is not displayed");
			}
			flag = flag & DASCommandControlUtils.waitForProgressBarToComplete(testCase);
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
