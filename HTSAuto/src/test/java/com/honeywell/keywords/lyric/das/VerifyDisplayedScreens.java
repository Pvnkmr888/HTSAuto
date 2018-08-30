package com.honeywell.keywords.lyric.das;

import java.util.ArrayList;
import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.report.FailType;
import com.honeywell.lyric.utils.das.DASRegistrationUtils;

public class VerifyDisplayedScreens extends Keyword {

	private TestCases testCase;
	public boolean flag = true;
	public ArrayList<String> expectedScreen;

	public VerifyDisplayedScreens(TestCases testCase, TestCaseInputs inputs,
			ArrayList<String> expectedScreen) {
		this.testCase = testCase;
		this.expectedScreen = expectedScreen;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user should be displayed with the (.*) screen$")
	public boolean keywordSteps() {
		try {
			if (testCase.isTestSuccessful()) {
				switch (expectedScreen.get(0).toUpperCase()) {
				case "SELECT DEVICE": {
					flag = flag & DASRegistrationUtils.verifySelectDeviceScreenVisible(testCase);
					break;
				}
				
				case "LOCATION SELECTION": {
					flag = flag & DASRegistrationUtils.verifyLocationSelectionScreenVisible(testCase);
					break;
				}
				
				case "DAS NAME": {
					flag = flag & DASRegistrationUtils.verifyDASNameScreenVisible(testCase);
					break;
				}
				case "POWER BASE STATION":{
					flag = flag & DASRegistrationUtils.verifyPowerBaseStationScreenVisible(testCase);
					break;
				}
				
				case "POWER BASE STATION INSTRUCTIONS":{
					flag = flag & DASRegistrationUtils.verifyPowerBaseStationInstructionScreenVisible(testCase);
					break;
				}
				
				case "SELECT BASE STATION":{
					flag = flag & DASRegistrationUtils.verifySelectBaseStationScreenVisible(testCase);
					break;
				}
				case "REGISTER BASE STATION":{
					flag = flag & DASRegistrationUtils.verifyRegisterBaseStationScreenVisible(testCase);
					break;
				}
				case "CONNECT TO NETWORK":{
					flag = flag & DASRegistrationUtils.verifyConnectToNetworkScreenVisible(testCase);
					break;
				}
				case "SETUP COMPLETE":{
					flag = flag & DASRegistrationUtils.verifySetupCompleteScreenVisible(testCase);
					break;
				}
				
				default: {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							expectedScreen.get(0) + " input not handled");
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
