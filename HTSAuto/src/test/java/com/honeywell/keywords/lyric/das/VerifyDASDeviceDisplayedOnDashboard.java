package com.honeywell.keywords.lyric.das;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

public class VerifyDASDeviceDisplayedOnDashboard extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	private ArrayList<String> expectedDevice;
	public boolean flag = true;

	public VerifyDASDeviceDisplayedOnDashboard(TestCases testCase, TestCaseInputs inputs,
			ArrayList<String> expectedDevice) {
		// this.inputs = inputs;
		this.testCase = testCase;
		this.expectedDevice = expectedDevice;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user should be displayed with (.*) DAS device on dashboard$")
	public boolean keywordSteps() {
		if (testCase.isTestSuccessful()) {
			HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
			List<WebElement> dashboardIconText = MobileUtils.getMobElements(fieldObjects, testCase,
					"DashboardIconText");
			boolean f = false;
			for (WebElement e : dashboardIconText) {
				String displayedText;
				if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
					displayedText = e.getText();
				} else {
					displayedText = e.getAttribute("value");
				}
				if (displayedText.equals(expectedDevice.get(0))) {
					f = true;
					break;
				}
			}
			if (f) {
				Keyword.ReportStep_Pass(testCase,
						"Device : " + expectedDevice.get(0) + " is present on the dashboard.");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						"Device : " + expectedDevice.get(0) + " is not present on the dashboard.");
			}
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Skipping this step since testcase has already failed");
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
