package com.honeywell.keywords.lyric.das;

import java.util.HashMap;

import org.openqa.selenium.Dimension;

import com.honeywell.commons.bddinterface.DataTable;
import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

public class VerifyDisplayOfFollowingElements extends Keyword {

	private TestCases testCase;
	//	private TestCaseInputs inputs;
	public boolean flag = true;
	public DataTable dataTable;
	public HashMap<String,MobileObject> fieldObjects;

	public VerifyDisplayOfFollowingElements(TestCases testCase, TestCaseInputs inputs, DataTable dataTable) {
		//	this.inputs = inputs;
		this.testCase = testCase;
		this.dataTable = dataTable;
		this.fieldObjects=MobileUtils.loadObjectFile(testCase, "Das_Settings");
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^verify the display of following:$")
	public boolean keywordSteps() {
		try{
			if(testCase.isTestSuccessful()){
				for (int i = 0; i < dataTable.getSize(); i++) {
					if((dataTable.getData(i, "Elements").trim()!="")&&(dataTable.getData(i, "Elements").trim()!=null)){
						switch (dataTable.getData(i, "Elements").trim().toUpperCase()) {
						case "MOTION DETECTION":{
							if (MobileUtils.isMobElementExists(fieldObjects,testCase,"MotionDetection_option",5)) {
								Keyword.ReportStep_Pass(testCase,dataTable.getData(i, "Elements")+" is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, dataTable.getData(i, "Elements").trim().toUpperCase()+" is not displayed");
							}
							break;
						}
						case "MICRO PHONE":{
							if (MobileUtils.isMobElementExists(fieldObjects,testCase,"CameraMicroPhone_Option",5)) {
								Keyword.ReportStep_Pass(testCase,dataTable.getData(i, "Elements")+" is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, dataTable.getData(i, "Elements").trim().toUpperCase()+" is not displayed");
							}
							break;
						}
						case "NIGHT VISION":{
							if (MobileUtils.isMobElementExists(fieldObjects,testCase,"NightVision_Option",5)) {
								Keyword.ReportStep_Pass(testCase,dataTable.getData(i, "Elements")+" is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, dataTable.getData(i, "Elements").trim().toUpperCase()+" is not displayed");
							}
							break;
						}
						case "VIDEO QUALITY":{
							if (MobileUtils.isMobElementExists(fieldObjects,testCase,"VideoQuality_Option",5)) {
								Keyword.ReportStep_Pass(testCase,dataTable.getData(i, "Elements")+" is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, dataTable.getData(i, "Elements").trim().toUpperCase()+" is not displayed");
							}
							break;
						}
						case "NIGHT VISION AUTO":{
							if (MobileUtils.isMobElementExists(fieldObjects,testCase,"Auto_Settings",5)) {
								Keyword.ReportStep_Pass(testCase,dataTable.getData(i, "Elements")+" is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, dataTable.getData(i, "Elements").trim().toUpperCase()+" is not displayed");
							}
							break;
						}
						case "NIGHT VISION ON":{
							if(MobileUtils.isMobElementExists(fieldObjects, testCase, "NightVision_ON")){
								Keyword.ReportStep_Pass(testCase, dataTable.getData(i, "Elements")+" is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, dataTable.getData(i, "Elements")+" is not displayed");

							}

							break;
						}
						case "NIGHT VISION OFF":{
							if(MobileUtils.isMobElementExists(fieldObjects, testCase, "NightVision_OFF")){
								Keyword.ReportStep_Pass(testCase,"Sound Sensitivity Label is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "Sound Sensitivity Label not displayed");

							}
							break;
						}
						case "VIDEO QUALITY AUTO":{
							if(MobileUtils.isMobElementExists(fieldObjects, testCase, "Auto_Settings")){
								Keyword.ReportStep_Pass(testCase, "Auto Quality is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "Auto Quality not displayed");

							}

							break;
						}
						case "VIDEO QUALITY LOW":{
							if(MobileUtils.isMobElementExists(fieldObjects, testCase, "VideoQuality_Low")){
								Keyword.ReportStep_Pass(testCase, "Low Quality is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "Low Quality not displayed");

							}

							break;
						}
						case "VIDEO QUALITY HIGH":{
							if(MobileUtils.isMobElementExists(fieldObjects, testCase, "VideoQuality_High")){
								Keyword.ReportStep_Pass(testCase, "High Quality is displayed");
							}else{
								flag=false;
								Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "High Quality not displayed");

							}

							break;
						}
						default:{
							Keyword.ReportStep_Fail(testCase, FailType.COSMETIC_FAILURE, "input not handled in switch case");
							flag=false;
						}
						}
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
