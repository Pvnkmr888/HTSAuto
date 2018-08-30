package com.honeywell.keywords.lyric.das;

import com.honeywell.commons.bddinterface.DataTable;
import com.honeywell.commons.coreframework.*;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

import java.util.ArrayList;
import java.util.HashMap;


public class TaponElement extends Keyword {

	private TestCases testCase;
	private ArrayList<String> expectedLocator;
	//private TestCaseInputs inputs;
	private HashMap<String, MobileObject> fieldObjects;
	private boolean flag=true;


	public TaponElement(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedLocator) {
		this.testCase = testCase;
		//this.inputs = inputs;
		this.expectedLocator = expectedLocator;

		fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
	}


	@Override
	@BeforeKeyword
	public boolean preCondition() throws KeywordException {
		return true;
	}

	@Override
	@KeywordStep(gherkins = "^user taps on (.*)$")
	public boolean keywordSteps() throws KeywordException {
		if (testCase.isTestSuccessful()) {
			switch (expectedLocator.get(0).toUpperCase()) {
			case "NIGHT VISION ON": {
				if (MobileUtils.isMobElementExists(fieldObjects, testCase, "NightVision_ON")){
					MobileUtils.clickOnElement(fieldObjects, testCase, "NightVision_ON");
					flag = true;
				}
				break;
			}
			case "NIGHT VISION OFF": {
			flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "NightVision_OFF");
				break;
			}
			case "NIGHT VISION AUTO": {
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "Auto_Settings");
				break;
			}
			case "VIDEO QUALITY LOW": {
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "VideoQuality_Low");
				break;
			}
			case "VIDEO QUALITY HIGH": {
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "VideoQuality_High");
				break;
			}
			case "VIDEO QUALITY AUTO": {
				flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "Auto_Settings");
				break;
			}
			
            case "MOTION DETECTION":{
				
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");

				if(MobileUtils.isMobElementExists(fieldObjects,testCase,"MotionDetectionLabel")){
					if (MobileUtils.clickOnElement(fieldObjects,testCase,"MotionDetectionLabel")) {
						Keyword.ReportStep_Pass(testCase,"Tapped on Motion Detection");
					} else {
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
								"Failed to Tapped on Motion detection");
					}
				}else{
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,"Motion Detecttion button not displayed");
					flag=false;
				}
				break;
			}
        	case "ENABLE DETECTION ZONE":{
				if(testCase.getPlatform().toUpperCase().contains("IOS")){
					if (MobileUtils.clickOnElement(testCase, "xpath","//*[contains(@name,'Enable this Detection Zone')]")) {
					}
					else{
						flag=false;
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Motion Detection Zone is not displayed");
					}
				}else{
					if (MobileUtils.clickOnElement(fieldObjects, testCase, "EnabledDetectionZone")) {
						Keyword.ReportStep_Pass(testCase, "Clicked on Enable detection zone");
					}
					else{
						flag=false;
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Motion Detection Zone is not displayed");
					}
				}
				break;
			}
        	case "BACK":{
				fieldObjects = MobileUtils.loadObjectFile(testCase, "GlobalDrawer");

				if (MobileUtils.isMobElementExists(fieldObjects,testCase,"BackButton")) {
					MobileUtils.clickOnElement(fieldObjects,testCase,"BackButton");
					Keyword.ReportStep_Pass(testCase,"Tapped on back button");
				}

				else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Failed to Tapped on BACK button");
				}
				if(testCase.getPlatform().equalsIgnoreCase("Android"))
				{
					try
					{
						fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
	                    System.out.println("Confirm button is"+MobileUtils.isMobElementExists(fieldObjects,testCase,"CameraOffAlertConfirm"));
						MobileUtils.clickOnElement(fieldObjects,testCase,"CameraOffAlertConfirm");
						Thread.sleep(6000);

					}
					catch(Exception e)
					{
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "did not click on confirm button");
					}
				}
				
				break;
			}
            
            case "ZONE1":
           {
            	System.out.println("entered zones case");
            	try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
				System.out.println("Entered zone 1");	
					if (MobileUtils.isMobElementExists(fieldObjects,testCase,"MotionDetection_Zone1")) {
						MobileUtils.clickOnElement(fieldObjects,testCase,"MotionDetection_Zone1");
						System.out.println("zone 1 present");

					}
					else{
						flag=false;
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"MotionDetection Zone1 not displayed");
					}

            	
            	break;
            }
            case "ZONE2":
            {
             
 				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
 					if (MobileUtils.isMobElementExists(fieldObjects,testCase,"MotionDetection_Zone2")) {
 						MobileUtils.clickOnElement(fieldObjects,testCase,"MotionDetection_Zone2");
 						System.out.println("zone 2 present");

 					}
 					else{
 						flag=false;
 						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"MotionDetection Zone1 not displayed");
 					}

             	
             	break;
             }
            case "ZONE3":
            {
             	
            	try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
                if (MobileUtils.isMobElementExists(fieldObjects,testCase,"MotionDetection_Zone3")) {
 						MobileUtils.clickOnElement(fieldObjects,testCase,"MotionDetection_Zone3");
 						System.out.println("zone 3 present");

 					}
 					else{
 						flag=false;
 						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"MotionDetection Zone1 not displayed");
 					}

             	
             	break;
             }
            case "ZONE4":
            {
             	
 				fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
                if (MobileUtils.isMobElementExists(fieldObjects,testCase,"MotionDetection_Zone4")) {
 						MobileUtils.clickOnElement(fieldObjects,testCase,"MotionDetection_Zone4");
 						System.out.println("zone 4 present");

 					}
 					else{
 						flag=false;
 						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"MotionDetection Zone1 not displayed");
 					}

             	
             	break;
             }
			default: {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
						expectedLocator.get(0) + " input not handled");
			}
			}
		} else {
			flag = false;
			Keyword.ReportStep_Fail_WithOut_ScreenShot(testCase, FailType.FUNCTIONAL_FAILURE,
					"Scenario steps failed already, hence skipping the verification");
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() throws KeywordException {
		return flag;
	}
}
