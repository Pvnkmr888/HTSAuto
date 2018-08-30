package com.honeywell.keywords.lyric.das;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
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

public class EnableSwitch extends Keyword {

	private TestCases testCase;
	private HashMap<String, MobileObject> fieldObjects;
	public ArrayList<String> exampleData;
	public boolean flag = true;
	private TestCaseInputs inputs;

	public EnableSwitch(TestCases testCase, TestCaseInputs inputs,ArrayList<String> exampleData) {
		super("User action on element");
		this.testCase = testCase;
		this.fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
		this.exampleData = exampleData;
		this.inputs=inputs;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		flag=true;
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user enables (.*)$")
	public boolean keywordSteps() {
		if(testCase.isTestSuccessful()){
		String currentSwitchStatus;
		String displayedVlaue;
		try{
			switch(exampleData.get(0).toUpperCase()){
			case "CAMERA MICROPHONE":{
				if(MobileUtils.isMobElementExists(fieldObjects,testCase,"CameraMicrophone_Switch")){
					currentSwitchStatus=MobileUtils.getFieldValue(fieldObjects,testCase,"CameraMicrophone_Switch");
					if(currentSwitchStatus.equalsIgnoreCase("OFF")||currentSwitchStatus.equalsIgnoreCase("false")){
						if(MobileUtils.clickOnElement(fieldObjects,testCase,"CameraMicrophone_Switch")){
							Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" enabled");
						}
					}else{
						/*Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is found to be enabled");
					flag=false;*/
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" enabled already");
					}
					MobileUtils.clickOnElement(fieldObjects, testCase, "NavigateUp");
				}else{
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,exampleData.get(1)+ "not displayed");
					flag=false;
				}
				break;
			}
			
			default:
			{
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,exampleData.get(0)+" inputs not handled Action on" +getClass().getName());
			}
			}
		}catch(Exception e){
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,"exception occured "+e.getMessage());
			flag=false;
		}
		}else{
			flag=false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,"Scenario steps failed already, thus skipping");
			
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
