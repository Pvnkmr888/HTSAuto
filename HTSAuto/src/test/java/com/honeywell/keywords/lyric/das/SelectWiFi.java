package com.honeywell.keywords.lyric.das;

import java.util.ArrayList;

import org.openqa.selenium.Dimension;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

public class SelectWiFi extends Keyword {

	private TestCases testCase;
	private ArrayList<String> expectedWiFi;
	public boolean flag = true;

	public SelectWiFi(TestCases testCase, TestCaseInputs inputs,ArrayList<String> expectedWiFi) {
		this.testCase = testCase;
		this.expectedWiFi=expectedWiFi;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
	
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user selects (.*) WiFi$")
	public boolean keywordSteps() {
		String locatorType,locatorValue;
		if(testCase.getPlatform().toUpperCase().contains("ANDROID"))
		{
			locatorType="xpath";
			locatorValue = "//android.widget.TextView[@text='"+expectedWiFi.get(0)+"']";
		}
		else
		{
			locatorType="name";
			locatorValue=expectedWiFi.get(0);
		}
		if(MobileUtils.isMobElementExists(locatorType, locatorValue, testCase,5,false))
		{
			flag = flag & MobileUtils.clickOnElement(testCase, locatorType, locatorValue);
		}
		else
		{
			Dimension d1 = testCase.getMobileDriver().manage().window().getSize();
			int startx = (int) (d1.width * 0.70);
			int starty = (int) (d1.height * 0.80);
			int endy = (int) (d1.height * 0.20);
			testCase.getMobileDriver().swipe(startx, starty, startx, endy, 3000);
			if(MobileUtils.isMobElementExists(locatorType, locatorValue, testCase,5,false))
			{
				flag = flag & MobileUtils.clickOnElement(testCase, locatorType, locatorValue);
			}
			else
			{
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Could not find WiFi from the list");
			}
		}
		
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
			return flag;
	}

}
