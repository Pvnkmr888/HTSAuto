package com.honeywell.keywords.lyric.das;

import java.util.ArrayList;
import java.util.HashMap;
import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordException;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

public class VerifyZoneIsSelected extends Keyword {
	
	public boolean flag =true;
	public TestCases testCase;
    public TestCaseInputs inputs;
	private ArrayList<String> exampleData;



     public VerifyZoneIsSelected(TestCases testCase, TestCaseInputs inputs,ArrayList<String> exampleData) {
     this.testCase = testCase;
     this.inputs = inputs;
     this.exampleData = exampleData;

     }


	@Override
	@BeforeKeyword
    public boolean preCondition() throws KeywordException {
		
		return flag;

	}


	@Override
    @KeywordStep(gherkins ="^verify \"(.+)\" is \"(.+)\"$")
    public boolean keywordSteps() throws KeywordException 
	{
		HashMap<String, MobileObject> fieldObjects  = MobileUtils.loadObjectFile(testCase, "Das_Settings");

		switch(exampleData.get(0).toUpperCase()){
		case "ZONE1":{
			if(exampleData.get(1).equalsIgnoreCase("selected")||exampleData.get(1).equalsIgnoreCase("retain selected")){
				if(!testCase.getPlatform().contains("IOS")){
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone1").getAttribute("name").equalsIgnoreCase("Selected")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is not selected");
						flag=false;
					}
				}else{
					System.out.println("IOS zone value is"+MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone1").getAttribute("value"));
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone1").getAttribute("value").equalsIgnoreCase("true")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is not selected");
						flag=false;
					}

				}
			}else if(exampleData.get(1).equalsIgnoreCase("deselected")){
				if(!testCase.getPlatform().contains("IOS")){
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone1").getAttribute("name").equalsIgnoreCase("UnSelected")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+"not selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is selected");
						flag=false;
					}
				}else{

					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone1").getAttribute("value").equalsIgnoreCase("false")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+"not selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is selected");
						flag=false;
					}

				}
			}
			else{
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,exampleData.get(1)+" inputs not handled on "+ exampleData.get(0));
			}
			break;
		  }
		case "ZONE2":{
			if(exampleData.get(1).equalsIgnoreCase("selected")||exampleData.get(1).equalsIgnoreCase("retain selected")){
				if(!testCase.getPlatform().contains("IOS")){
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone2").getAttribute("name").equalsIgnoreCase("Selected")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is not selected");
						flag=false;
					}
				}else{
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone2").getAttribute("value").equalsIgnoreCase("true")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is not selected");
						flag=false;
					}
				}
			}else if(exampleData.get(1).equalsIgnoreCase("deselected")){
				if(!testCase.getPlatform().contains("IOS")){
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone2").getAttribute("name").equalsIgnoreCase("UnSelected")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+"not selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is selected");
						flag=false;
					}
				}else{

					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone2").getAttribute("value").equalsIgnoreCase("false")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+"not selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is selected");
						flag=false;
					}

				}
			}
			else{
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,exampleData.get(1)+" inputs not handled on "+ exampleData.get(0));
			}
			break;
		}
		case "ZONE3":{
			if(exampleData.get(1).equalsIgnoreCase("selected")||exampleData.get(1).equalsIgnoreCase("retain selected")){
				if(!testCase.getPlatform().contains("IOS")){
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone3").getAttribute("name").equalsIgnoreCase("Selected")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is not selected");
						flag=false;
					}
				}else{
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone3").getAttribute("value").equalsIgnoreCase("true")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is not selected");
						flag=false;
					}
				}
			}else if(exampleData.get(1).equalsIgnoreCase("deselected")){
				if(!testCase.getPlatform().contains("IOS")){
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone3").getAttribute("name").equalsIgnoreCase("UnSelected")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+"not selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is selected");
						flag=false;
					}
				}else{

					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone3").getAttribute("value").equalsIgnoreCase("false")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+"not selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is selected");
						flag=false;
					}

				}
			}
			else{
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,exampleData.get(1)+" inputs not handled on "+ exampleData.get(0));
			}
			break;
		}
		case "ZONE4":{
			if(exampleData.get(1).equalsIgnoreCase("selected")||exampleData.get(1).equalsIgnoreCase("retain selected")){
				if(!testCase.getPlatform().contains("IOS")){
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone4").getAttribute("name").equalsIgnoreCase("Selected")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is not selected");
						flag=false;
					}
				}else{
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone4").getAttribute("value").equalsIgnoreCase("true")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+" selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is not selected");
						flag=false;
					}
				}
			}else if(exampleData.get(1).equalsIgnoreCase("deselected")){
				if(!testCase.getPlatform().contains("IOS")){
					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone4").getAttribute("name").equalsIgnoreCase("UnSelected")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+"not selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is selected");
						flag=false;
					}
				}else{

					if(MobileUtils.getMobElement(fieldObjects,testCase,"MotionDetection_Zone4").getAttribute("value").equalsIgnoreCase("false")){
						Keyword.ReportStep_Pass(testCase, exampleData.get(0)+"not selected");
					}else{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, exampleData.get(0)+" is selected");
						flag=false;
					}

				}
			}
			else{
				Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,exampleData.get(1)+" inputs not handled on "+ exampleData.get(0));
			}
			break;
		}
		case "ENABLE DETECTION ZONE":{
			if(testCase.getPlatform().toUpperCase().contains("IOS")){
				System.out.println("IOS selected value is"+MobileUtils.getMobElement(fieldObjects,testCase,"EnabledDetectionZone").isSelected());
				if (MobileUtils.getMobElement(fieldObjects,testCase,"EnabledDetectionZone").isSelected()) {
					Keyword.ReportStep_Pass(testCase, "Enabledetection zone is selected");
				}

				else{
					flag=false;
					Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Enable Detection Zone is not selected");
				}
			}
			else
			{
				System.out.println("value is"+MobileUtils.getMobElement(fieldObjects,testCase,"EnabledDetectionZone").isDisplayed());
				if (MobileUtils.getMobElement(fieldObjects, testCase, "EnabledDetectionZone").getAttribute("checked").equalsIgnoreCase("true")) {
					//System.out.println(MobileUtils.getMobElement(fieldObjects,testCase,"EnabledDetectionZone").getAttribute("content-desc"));
					Keyword.ReportStep_Pass(testCase, "Enabledetection zone is selected");

				}
				else
				{
					flag=false;
					Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Enable Detection Zone is not selected");
				}
			}
			break;
		}
		default:{
			Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,exampleData.get(0)+" inputs not handled Action on" +getClass().getName());
		}
		}
		return flag;
	}
	@Override
    @AfterKeyword

	public boolean postCondition() throws KeywordException {


	return flag;

	}
}
