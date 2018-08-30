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

public class EnterWiFiPassword extends Keyword {

	private TestCases testCase;
	private ArrayList<String> expectedWiFiPassword;
	public boolean flag = true;

	public EnterWiFiPassword(TestCases testCase, TestCaseInputs inputs,ArrayList<String> expectedWiFiPassword) {
		this.testCase = testCase;
		this.expectedWiFiPassword=expectedWiFiPassword;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
	
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user inputs (.*) as the WiFi Password$")
	public boolean keywordSteps() {
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
		flag = flag & MobileUtils.setValueToElement(fieldObjects, testCase, "WiFiPasswordTextField", expectedWiFiPassword.get(0));
		if(testCase.getPlatform().toUpperCase().contains("ANDROID"))
		{
			MobileUtils.hideKeyboard(testCase.getMobileDriver());
		}
		else
		{
			MobileUtils.clickOnElement(testCase, "name", "Done");
		}
		
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
			return flag;
	}

}
