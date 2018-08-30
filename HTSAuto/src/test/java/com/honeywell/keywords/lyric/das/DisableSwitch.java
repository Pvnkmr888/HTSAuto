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

public class DisableSwitch extends Keyword {

	private TestCases testCase;
	private HashMap<String, MobileObject> fieldObjects;
	public ArrayList<String> exampleData;
	public boolean flag = true;
	private TestCaseInputs inputs;
	public DisableSwitch(TestCases testCase, TestCaseInputs inputs,ArrayList<String> exampleData) {
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
	@KeywordStep(gherkins = "^user disables (.*)$")
	public boolean keywordSteps() {
		if(testCase.isTestSuccessful()){
		String displayedVlaue;
		try{
			switch(exampleData.get(0).toUpperCase()){
			
			// this will disable Camera Microphone through UI
			case "CAMERA MICROPHONE":{
				if(MobileUtils.isMobElementExists(fieldObjects,testCase,"CameraMicrophone_Switch")){
					displayedVlaue=MobileUtils.getFieldValue(fieldObjects,testCase,"CameraMicrophone_Switch");
					if(displayedVlaue.equalsIgnoreCase("ON") ||displayedVlaue.equalsIgnoreCase("true")){
						MobileUtils.clickOnElement(fieldObjects,testCase,"CameraMicrophone_Switch");
						if (MobileUtils.isMobElementExists(fieldObjects, testCase, "Microphone_Alert")){
							Keyword.ReportStep_Pass(testCase, "Microphone Pop is displayed");
							MobileUtils.clickOnElement(fieldObjects,testCase,"OK_button");
						}
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" disabled");
					}else{
						/*Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is found to be disabled");
					flag=false;*/
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" disabled already");
					}
					MobileUtils.clickOnElement(fieldObjects, testCase, "NavigateUp");
				}else{
					Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is not found");
					flag=false;
				}
				break;
			}
			default:{
				Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" inputs not handled Action on" +getClass().getName());
				flag=false;
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
