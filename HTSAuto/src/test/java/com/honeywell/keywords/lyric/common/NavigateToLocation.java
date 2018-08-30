package com.honeywell.keywords.lyric.common;

import java.util.ArrayList;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordException;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.lyric.utils.JasperUtils;

public class NavigateToLocation extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	public String locationToBeSelected;
	public boolean flag = true;

	public NavigateToLocation(TestCases testCase, TestCaseInputs inputs, ArrayList<String> locationName) {
		this.testCase = testCase;
		this.locationToBeSelected = locationName.get(0);
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() throws KeywordException {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user navigates to location (.*)$")
	public boolean keywordSteps() throws KeywordException {
		flag = flag & JasperUtils.selectLocation(testCase, locationToBeSelected);
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() throws KeywordException {
		return flag;
	}
}
