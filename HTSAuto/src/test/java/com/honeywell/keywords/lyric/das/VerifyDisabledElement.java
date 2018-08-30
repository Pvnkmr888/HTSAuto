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
import com.honeywell.commons.report.FailType;

import io.appium.java_client.MobileElement;

public class VerifyDisabledElement extends Keyword {

	private TestCases testCase;
	private HashMap<String, MobileObject> fieldObjects;
	public boolean flag = true;
	public ArrayList<String> expectedLocator;

	public VerifyDisabledElement(TestCases testCase, TestCaseInputs inputs,ArrayList<String> expectedLocator) {
		this.testCase = testCase;
		this.expectedLocator=expectedLocator;
		this.fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		flag=true;
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^verify (.*) is disabled$")
	public boolean keywordSteps() {
		try{
			if(testCase.isTestSuccessful()){
				switch(expectedLocator.get(0).toUpperCase()){
				// this will check the whether mic icon is displayed in push to talk screen
				case "CAMERA MICROPHONE":{
					flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "Base_StationCamera");
					MobileUtils.isMobElementExists(fieldObjects, testCase, "CameraMicrophone_Switch", 15);
					String ele = MobileUtils.getFieldValue(fieldObjects,testCase,"CameraMicrophone_Switch");
					if (ele.equalsIgnoreCase("OFF")||ele.equalsIgnoreCase("false")) {
						flag=true;
						Keyword.ReportStep_Pass(testCase,expectedLocator.get(0)+" is disabled");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, expectedLocator.get(0)+" not disabled");
						flag=false;
					}
					
					break;
				}
				default :{
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,expectedLocator.get(0) +" input not handled");
				}
				}
			}else{
				flag=false;
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,"Scenario steps failed already, hence skipping the verification");
			}
		}catch(Exception e){
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,"exception occured "+e.getMessage());
			flag=false;
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
