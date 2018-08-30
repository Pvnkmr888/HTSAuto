package com.honeywell.keywords.lyric.das;

import com.honeywell.commons.coreframework.*;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

import java.util.ArrayList;
import java.util.HashMap;


public class VerifyDisplayedElement extends Keyword {

	private TestCases testCase;
//	private ArrayList<String> passedParams;
	//private TestCaseInputs inputs;
	private boolean flag=true;
	public ArrayList<String> expectedLocator;
	private HashMap<String, MobileObject> fieldObjects;

	public VerifyDisplayedElement(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedLocator) {
		this.testCase = testCase;
		//this.inputs = inputs;
		this.expectedLocator=expectedLocator;
		this.fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() throws KeywordException {
		return true;
	}

	@Override
	@KeywordStep(gherkins = "^verify (.*) is selected$")

	public boolean keywordSteps() throws KeywordException {
		try{
			if(testCase.isTestSuccessful()){
				switch (expectedLocator.get(0).toUpperCase()) {
				case "NIGHT VISION ON":{
					if (MobileUtils.isMobElementExists(fieldObjects,testCase,"NightVisionOn_Check")) {
						Keyword.ReportStep_Pass(testCase,expectedLocator.get(0)+" is enabled");
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Night vision selected value not displayed");
					}
					break;
				}
				case "NIGHT VISION OFF":{
					if (MobileUtils.isMobElementExists(fieldObjects,testCase,"NightVisionOff_Check")) {
						Keyword.ReportStep_Pass(testCase,expectedLocator.get(0)+" is enabled");
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Night vision selected value not displayed");
					}
					break;
				}
				case "NIGHT VISION AUTO":{
					if (MobileUtils.isMobElementExists(fieldObjects,testCase,"NightVisionAuto_Check")) {
						Keyword.ReportStep_Pass(testCase,expectedLocator.get(0)+" is enabled");
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Night vision selected value not displayed");
					}
					break;
				}
				case "VIDEO QUALITY LOW":{
					if (MobileUtils.isMobElementExists(fieldObjects,testCase,"VideoQualityLow_Check")) {
						Keyword.ReportStep_Pass(testCase,expectedLocator.get(0)+" is enabled");
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Night vision selected value not displayed");
					}
					break;
				}
				case "VIDEO QUALITY HIGH":{
					if (MobileUtils.isMobElementExists(fieldObjects,testCase,"VideoQualityHigh_Check")) {
						Keyword.ReportStep_Pass(testCase,expectedLocator.get(0)+" is enabled");
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Night vision selected value not displayed");
					}
					break;
				}
				case "VIDEO QUALITY AUTO":{
					if (MobileUtils.isMobElementExists(fieldObjects,testCase,"VideoQualityAuto_Check")) {
						Keyword.ReportStep_Pass(testCase,expectedLocator.get(0)+" is enabled");
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Night vision selected value not displayed");
					}
					break;
				}
				default: {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							expectedLocator.get(0) + " input not handled");
				}
				}
			}else {
				flag = false;
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
						"Scenario steps failed already, hence skipping the verification");
			}
		}catch(Exception e){
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,"exception occured "+e.getMessage());
			flag=false;
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() throws KeywordException {
		return flag;
	}
}
