package com.honeywell.keywords.lyric.das;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

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

public class SelectLocationFromTheList extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	private boolean flag = true;
	private ArrayList<String> testData;

	public SelectLocationFromTheList(TestCases testCase, TestCaseInputs inputs, ArrayList<String> data) {
		this.testCase = testCase;
		// this.inputs = inputs;
		this.testData = data;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() throws KeywordException {
		return true;
	}

	@Override
	@KeywordStep(gherkins = "^user selects (.*) location$")
	public boolean keywordSteps() throws KeywordException {
		if(testCase.getPlatform().toUpperCase().contains("IOS"))
		{
			Keyword.ReportStep_Pass(testCase, "Location Screen not available in IOS, hence skipping this step");
			return true;
		}
		
		HashMap<String, MobileObject> fieldObjects;
		switch (testData.get(0).toUpperCase()) {
		case "DEFAULT": {
			fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
			List<WebElement> locations = MobileUtils.getMobElements(fieldObjects, testCase,
					"InstallationScreen_LocationNameDefault");
			if (locations != null) {
				for (WebElement location : locations) {
					Keyword.ReportStep_Pass(testCase, location.getText());
				}
				Keyword.ReportStep_Pass(testCase, locations.get(0).getText());
				locations.get(0).click();
				flag = true;
				Keyword.ReportStep_Pass(testCase, "By default location 1 is selected");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Not able to find location list");
			}

			break;
		}
		default: {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Not handled for the input " + testData.get(0).toUpperCase());
		}
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() throws KeywordException {
		return flag;
	}
}
