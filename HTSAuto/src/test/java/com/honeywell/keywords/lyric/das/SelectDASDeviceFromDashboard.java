package com.honeywell.keywords.lyric.das;

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

public class SelectDASDeviceFromDashboard extends Keyword {

	private TestCases testCase;
	private TestCaseInputs inputs;
	public boolean flag = true;

	public SelectDASDeviceFromDashboard(TestCases testCase, TestCaseInputs inputs) {
		this.testCase = testCase;
		this.inputs = inputs;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {

		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user selects DAS device from the dashboard$")
	public boolean keywordSteps() {
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		List<WebElement> dashboardIconText = null;
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "DashboardIconText", 5)) {
			dashboardIconText = MobileUtils.getMobElements(fieldObjects, testCase, "DashboardIconText");
		}
		String deviceToBeSelected = inputs.getInputValue("LOCATION1_DEVICE1_NAME");
		boolean f = false;
		for (WebElement e : dashboardIconText) {
			String displayedText;
			if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
				displayedText = e.getText();
			} else {
				displayedText = e.getAttribute("value");
			}
			if (displayedText.equals(deviceToBeSelected)) {
				e.click();
				if (!MobileUtils.isMobElementExists("id", "status_image", testCase, 3)) {
					e.click();
				}
				f = true;
				break;
			}
		}
		if (f) {
			Keyword.ReportStep_Pass(testCase, "Successfully selected device : " + deviceToBeSelected);
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Device : " + deviceToBeSelected + " is not present on the dashboard.");
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
