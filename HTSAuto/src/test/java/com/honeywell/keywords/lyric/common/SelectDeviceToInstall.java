package com.honeywell.keywords.lyric.common;

import java.util.ArrayList;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;
import com.honeywell.lyric.utils.JasperUtils;

public class SelectDeviceToInstall extends Keyword {

	private TestCases testCase;
	// private TestCaseInputs inputs;
	private ArrayList<String> expectedDevice;
	public boolean flag = true;

	public SelectDeviceToInstall(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedDevice) {
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
	@KeywordStep(gherkins = "^user selects (.*) device from install device screen$")
	public boolean keywordSteps() {
		switch (expectedDevice.get(0).toUpperCase()) {
		case "HONEYWELL DAS":
			if (testCase.getPlatform().toUpperCase().contains("IOS")) {
				if (MobileUtils.isMobElementExists("xpath",
						"//XCUIElementTypeStaticText[contains(@name,'Showing devices for')]", testCase, 5)) {
					if (!MobileUtils
							.getMobElement(testCase, "xpath",
									"//XCUIElementTypeStaticText[contains(@name,'Showing devices for')]")
							.getAttribute("value").contains("United States")) {
						flag = flag & MobileUtils.clickOnElement(testCase, "name", "CHANGE COUNTRY");
						if(MobileUtils.isMobElementExists("xpath", "//XCUIElementTypeTextField", testCase,10))
						{
						flag = flag & MobileUtils.setValueToElement(testCase, "xpath", "//XCUIElementTypeTextField",
								"United States");
						}
						else
						{
							flag = false;
							Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Search Text Field not present when selecting country");
						}
						flag = flag & MobileUtils.clickOnElement(testCase, "xpath", "//XCUIElementTypeStaticText[@name='United States']");
						if (MobileUtils.isMobElementExists("xpath",
								"//XCUIElementTypeStaticText[contains(@name,'Showing devices for')]", testCase, 10)) {
							if (MobileUtils
									.getMobElement(testCase, "xpath",
											"//XCUIElementTypeStaticText[contains(@name,'Showing devices for')]")
									.getAttribute("value").contains("United States")) {
								Keyword.ReportStep_Pass(testCase, "Successfully changed country to United States");
							} else {
								flag = false;
								Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
										"Failed to change country to United States");
							}

						} else {
							flag = false;
							Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
									"Failed to change country to United States");
						}
					}
				}
			}

			flag = flag & JasperUtils.selectDeviceToInstall(testCase, "Honeywell DAS");
			break;
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
