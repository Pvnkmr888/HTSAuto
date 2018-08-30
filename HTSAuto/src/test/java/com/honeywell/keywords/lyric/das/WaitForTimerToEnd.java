package com.honeywell.keywords.lyric.das;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.lyric.utils.das.DASCommandControlUtils;

public class WaitForTimerToEnd extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	public boolean flag = true;

	public WaitForTimerToEnd(TestCases testCase, TestCaseInputs inputs) {
		// this.inputs = inputs;
		this.testCase = testCase;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^timer ends on user device$")
	public boolean keywordSteps() {
		flag = flag & DASCommandControlUtils.waitForTimerToComplete(testCase);
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
