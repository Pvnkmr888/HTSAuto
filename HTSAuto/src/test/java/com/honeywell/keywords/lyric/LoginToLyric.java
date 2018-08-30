package com.honeywell.keywords.lyric;

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
import com.honeywell.lyric.utils.JasperUtils;

public class LoginToLyric extends Keyword {

	private TestCases testCase;
	private TestCaseInputs inputs;
	private HashMap<String, MobileObject> fieldObjects;

	public boolean flag = true;

	public LoginToLyric(TestCases testCase, TestCaseInputs inputs) {
		this.inputs = inputs;
		this.testCase = testCase;
		this.fieldObjects = MobileUtils.loadObjectFile(testCase, "HomeScreen");
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		flag = MobileUtils.launchApplication(inputs, testCase, true);
		if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
			if (MobileUtils.isMobElementExists("ID", "com.android.packageinstaller:id/permission_allow_button",
					testCase, 10)) {
				if (!MobileUtils.clickOnElement(testCase, "ID",
						"com.android.packageinstaller:id/permission_allow_button")) {
					flag = false;
				}
			}
			
		} else {
			if (MobileUtils.isMobElementExists("name", "OK", testCase, 5)) {
				if (!MobileUtils.clickOnElement(testCase, "name", "OK")) {
					flag = false;
				}
			}
			if (MobileUtils.isMobElementExists("name", "Allow", testCase, 5)) {
				if (!MobileUtils.clickOnElement(testCase, "name", "Allow")) {
					flag = false;
				}
			}
			if (MobileUtils.isMobElementExists("name", "UpdateIgnore", testCase, 5)) {
				if (!MobileUtils.clickOnElement(testCase, "name", "UpdateIgnore")) {
					flag = false;
				}
			}

			/*if (MobileUtils.isMobElementExists("name", "Done", testCase, 5)) {
				flag = flag & MobileUtils.clickOnElement(testCase, "name", "Done");
			}*/
		}
		flag = flag & JasperUtils.setAppEnvironment(testCase, inputs, false);
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "LoginButton", 5)) {
			flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "LoginButton");
		}

		if (inputs.getInputValue(JasperUtils.DEVICE_NAME).isEmpty()) {
			inputs.setInputValue(JasperUtils.LOCATION_NAME, inputs.getInputValue("LOCATION1_NAME"));
			inputs.setInputValue(JasperUtils.DEVICE_NAME, inputs.getInputValue("LOCATION1_DEVICE1_NAME"));
		}
		
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user logs in to Lyric app$", description = "This keyword is used to launch and login to the app")
	public boolean keywordSteps() {
//		flag = flag & MobileUtils.clearTextField(fieldObjects, testCase, "EmailAddress");
		if (MobileUtils.setValueToElement(fieldObjects, testCase, "EmailAddress",
				inputs.getInputValue("USERID").toString())) {
			if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
				MobileUtils.hideKeyboard(testCase.getMobileDriver());
			}
			ReportStep_Pass(testCase, "Login To Lyric : Email Address set to - " + inputs.getInputValue("USERID"));
		} else {
			ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Login To Lyric : Not able to set Email Address.");
			flag = false;
		}
		//flag = flag & MobileUtils.clearTextField(fieldObjects, testCase, "Password");
		if (MobileUtils.setValueToElement(fieldObjects, testCase, "Password",
				inputs.getInputValue("PASSWORD").toString())) {
			if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
				MobileUtils.hideKeyboard(testCase.getMobileDriver());
			}
			ReportStep_Pass(testCase, "Login To Lyric : Password set to - " + inputs.getInputValue("PASSWORD"));
		} else {
			ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Login To Lyric : Not able to set Password.");
			flag = false;
		}
		if(testCase.getPlatform().toUpperCase().contains("ANDROID"))
		{
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "LoginButton", 5)) {
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "LoginButton");
			}
		}
		else
		{
			flag = flag & MobileUtils.clickOnElement(testCase, "NAME", "Login_Button");
		}
		
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		if (MobileUtils.isMobElementExists(fieldObjects, testCase, "AlertsIcon", 20,true)) {
			if (inputs.isRunningOnSauceLabs()) {
				testCase.setDriverSessionID(testCase.getMobileDriver().getSessionId().toString());
			}
			Keyword.ReportStep_Pass(testCase, "Login to Lyric : Successfully navigated to HomeScreen");
			return flag;
		} else {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Login To Lyric : Unable to navigate to homepage. Could not find notification icon on homepage");
		}
		return flag;
	}

}
