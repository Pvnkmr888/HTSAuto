package com.honeywell.keywords.lyric.das;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;
import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

public class VerifySetupProgressBar extends Keyword {

	private TestCases testCase;
	private HashMap<String, MobileObject> fieldObjects;

	public boolean flag = true;

	public VerifySetupProgressBar(TestCases testCase, TestCaseInputs inputs) {
		// this.inputs = inputs;
		this.testCase = testCase;
		this.fieldObjects = MobileUtils.loadObjectFile(testCase, "DAS_InstallationScreen");
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {

		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user should be displayed with a setup progress bar$")
	public boolean keywordSteps() {
		try {
			if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ProgressBar", 5)) {
				Keyword.ReportStep_Pass(testCase, "Setup progress bar is displayed");
			} else {
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Setup progress bar is not displayed");
				return false;

			}
			FluentWait<String> fWait = new FluentWait<String>(" ");
			fWait.pollingEvery(5, TimeUnit.SECONDS);
			fWait.withTimeout(3, TimeUnit.MINUTES);
			Boolean isEventReceived = fWait.until(new Function<String, Boolean>() {
				public Boolean apply(String a) {
					try {
						if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ProgressBar", 2,false)) {
							System.out.println("Waiting for setup to complete");
							return false;
						} else {
							System.out.println("Setup Complete!");
							return true;
						}
					} catch (Exception e) {
						return false;
					}
				}
			});
			if (isEventReceived) {
				if (testCase.getPlatform().toUpperCase().contains("IOS")) {
					if (MobileUtils.isMobElementExists("name", "Registration Timeout", testCase, 5)) {
						flag = false;
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Registration not successful");
					} else {
						Keyword.ReportStep_Pass(testCase, "Setup successfully completed");
					}
				} else {
					
					if (MobileUtils.isMobElementExists("id", "android:id/message", testCase, 5)) {
						flag = false;
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Registration not successful");
					} else {
						Keyword.ReportStep_Pass(testCase, "Setup successfully completed");
					}
					Keyword.ReportStep_Pass(testCase, "Setup successfully completed");
				}
			}
		} catch (TimeoutException e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Setup not completed after waiting for 3 minutes");
		} catch (Exception e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
