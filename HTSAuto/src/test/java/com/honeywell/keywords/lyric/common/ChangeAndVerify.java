package com.honeywell.keywords.lyric.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;

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

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;

public class ChangeAndVerify extends Keyword {

	private TestCases testCase;
	private TestCaseInputs inputs;
	public ArrayList<String> expectedLocator;
	public boolean flag = true;
	private HashMap<String, MobileObject> fieldObjects;

	public ChangeAndVerify(TestCases testCase, TestCaseInputs inputs, ArrayList<String> expectedLocator) {
		this.testCase = testCase;
		this.inputs = inputs;
		this.expectedLocator = expectedLocator;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() throws KeywordException {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^verify changed (.*) is updated for (.*)$")
	public boolean keywordSteps() throws KeywordException {
		try{
			if (testCase.isTestSuccessful()) {
				switch (expectedLocator.get(0).toUpperCase()) {
				case "DELAYVALUE": {
					String valueToSet = null ;
					fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
					switch(inputs.getInputValue(ClickOnMenu.CURRENTDELAYVALUE).replaceAll(" Seconds", "").trim()){
					case "15":{
						valueToSet= "Value45";
						break;
					}
					case "30":{
						valueToSet= "Value15";
						break;
					}
					case "45":{
						valueToSet= "Value30";
						break;
					}	
					case "60":{
						valueToSet= "Value30";
						break;
					}
					default: {
						flag = false;
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
								inputs.getInputValue("CURRENTDELAYVALUE")  + " input not handled in "+expectedLocator.get(0).toUpperCase());
					}
					}
					if(flag){
						flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, valueToSet);
						if(!testCase.getPlatform().toUpperCase().contains("IOS")){
						while(MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",30)){
							Keyword.ReportStep_Pass(testCase, "waiting for the updating progress to disappear");
							MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",15);
						}
						}
						MobileUtils.clickOnElement(fieldObjects, testCase, "NavigateUp");
						if(testCase.getPlatform().toUpperCase().contains("IOS")){
							while(MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",5)){
								Keyword.ReportStep_Pass(testCase, "waiting for the updating progress to disappear");
								MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",15);
							}
							Keyword.ReportStep_Pass(testCase, "waiting 25secs");
							MobileUtils.isMobElementExists(fieldObjects, testCase, "CameraState",25);
						}
						if(valueToSet.replaceAll("Value", "").contains(MobileUtils.getFieldValue(fieldObjects, testCase, "CurrentDelayValue").replaceAll(" Seconds", "").trim())){
							Keyword.ReportStep_Pass(testCase, "Value is updated successfully to "+MobileUtils.getFieldValue(fieldObjects, testCase, "CurrentDelayValue") );
						}else{
							flag = false;
							Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,"changed value is "+valueToSet+" but found "+MobileUtils.getFieldValue(fieldObjects, testCase, "CurrentDelayValue"));
						}
					}
					break;
				}
				case "CHIMEVOLUME": {
					fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
					Keyword.ReportStep_Pass(testCase,MobileUtils.getFieldValue(fieldObjects, testCase, "ChimeVolumeValue"));	
					Point chimeVolumeSeekbar = MobileUtils.getMobElement(fieldObjects, testCase, "ChimeVolumeSeekbar").getCenter();
					int placeToTap=20;
					String PlaceToDrag="70%";
					TouchAction a2 = new TouchAction(testCase.getMobileDriver());
					String oldValue;
					oldValue=MobileUtils.getFieldValue(fieldObjects, testCase, "ChimeVolumeValue");
					if(testCase.getPlatform().toUpperCase().contains("IOS")){
						if(Integer.parseInt(oldValue.replace("%", "").trim())<50){
							PlaceToDrag="90%";
						}else{
							PlaceToDrag="30%";
						}
					}else{
						if(Integer.parseInt(oldValue.replace("%", "").trim())<50){
							placeToTap =(int) (chimeVolumeSeekbar.getX()/1.25);
						}else{
							placeToTap =(int) (chimeVolumeSeekbar.getX()/3);
						}
					}
					if(testCase.getPlatform().toUpperCase().contains("IOS")){
						if(testCase.getPlatform().toUpperCase().contains("IOS")){
							String xpath = "//UIASlider";
							IOSElement  element = (IOSElement)testCase.getMobileDriver().findElement(By.xpath(xpath));
							element.setValue(PlaceToDrag);
						}
					}else{
						a2.tap(placeToTap, chimeVolumeSeekbar.getY()).perform();
						Keyword.ReportStep_Pass(testCase,"Total x is "+chimeVolumeSeekbar.getX()+"clicked at x pos "+placeToTap);	
						if(MobileUtils.getFieldValue(fieldObjects, testCase, "ChimeVolumeValueAfterChange").equalsIgnoreCase(oldValue)){
							Keyword.ReportStep_Pass(testCase,"no change in value hence changing again");	
							placeToTap =(int) (chimeVolumeSeekbar.getX()/2);
							a2.tap(placeToTap, chimeVolumeSeekbar.getY()).perform();
							Keyword.ReportStep_Pass(testCase,"Total x is "+chimeVolumeSeekbar.getX()+"clicked at x pos "+placeToTap);	
						}
						Keyword.ReportStep_Pass(testCase,"New Value is at "+MobileUtils.getFieldValue(fieldObjects, testCase, "ChimeVolumeValueAfterChange"));	
					}
					
					MobileUtils.clickOnElement(fieldObjects, testCase, "NavigateUp");
					while(MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",5)){
						Keyword.ReportStep_Pass(testCase, "waiting for the updating progress to disappear");
						MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",15);
					}
					Keyword.ReportStep_Pass(testCase, "waiting 25secs");
					MobileUtils.isMobElementExists(fieldObjects, testCase, "CameraState",25);

					switch (expectedLocator.get(1).toUpperCase()) {
					case "HOME SETTINGS":{
						MobileUtils.isMobElementExists(fieldObjects, testCase, "HomeSettings");
						flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "HomeSettings");
						break;
					}
					case "AWAY SETTINGS":{
						flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "AwaySettings");
						break;
					}
					case "NIGHT SETTINGS":{
						flag = flag & MobileUtils.clickOnElement(fieldObjects, testCase, "SleepSettings");
						break;
					}

					}
					String newValue=MobileUtils.getFieldValue(fieldObjects, testCase, "ChimeVolumeValue");

					/*if(increaseValue){
					if(Integer.parseInt(oldValue)<Integer.parseInt(newValue)){
						Keyword.ReportStep_Pass(testCase,"Increased from "+oldValue+" to "+newValue);	
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Failed to Increase from "+oldValue+" to "+newValue);	
					}
				}else{
					if(Integer.parseInt(oldValue)>Integer.parseInt(newValue)){
						Keyword.ReportStep_Pass(testCase,"Decreased from "+oldValue+" to "+newValue);	
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Failed to Decrease from "+oldValue+" to "+newValue);	
					}
				}*/
					if(Integer.parseInt(oldValue.replace("%", "").trim())!=Integer.parseInt(newValue.replace("%", "").trim())){
						Keyword.ReportStep_Pass(testCase,"Value updated from "+oldValue+" to "+newValue);	
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Failed to update from "+oldValue+" to "+newValue);	
					}
					break;
				}

				case "NAME":{
					fieldObjects = MobileUtils.loadObjectFile(testCase, "Das_Settings");
					String currentName =MobileUtils.getFieldValue(fieldObjects, testCase, "RenameBaseStation");
					Keyword.ReportStep_Pass(testCase,"Current basestation name is  "+currentName);	
					MobileUtils.clickOnElement(fieldObjects, testCase, "RenameBaseStation");
					MobileUtils.clearTextField(fieldObjects, testCase, "RenameBaseStation", "");
					String NameToUpdate;
					if(currentName.contains("updated")){
						NameToUpdate=currentName.replaceAll("updated","");
					}else{
						NameToUpdate=currentName+"updated";
					}
					MobileUtils.setValueToElement(fieldObjects, testCase, "RenameBaseStation", NameToUpdate, "");
					if(testCase.getPlatform().toUpperCase().contains("IOS")){
						MobileUtils.hideKeyboardIOS(testCase.getMobileDriver(), "Done");
						//testCase.getMobileDriver().scrollToExact("Base Station");
						while(MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",30)){
							Keyword.ReportStep_Pass(testCase, "waiting for the updating progress to disappear");
							MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",15);
						}
					}
					MobileUtils.hideKeyboard(testCase.getMobileDriver(), "After renaming ");
					MobileUtils.clickOnElement(fieldObjects, testCase, "NavigateUp");

					MobileUtils.clickOnElement(fieldObjects, testCase, "BaseStation");
					String NewName =MobileUtils.getFieldValue(fieldObjects, testCase, "RenameBaseStation");
					if(NewName.equals(NameToUpdate)){
						Keyword.ReportStep_Pass(testCase,"Value updated to "+NewName);	
					}else{
						Keyword.ReportStep_Fail(testCase,FailType.FUNCTIONAL_FAILURE,"Failed to update to "+NewName);	
					}

					Keyword.ReportStep_Pass(testCase,"Cleanup added for reverting the name");
					MobileUtils.clearTextField(fieldObjects, testCase, "RenameBaseStation", "");
					MobileUtils.setValueToElement(fieldObjects, testCase, "RenameBaseStation", currentName, "");
					Keyword.ReportStep_Pass(testCase,"Reverted basestation name to  "+currentName);	
					if(testCase.getPlatform().toUpperCase().contains("IOS")){
						MobileUtils.hideKeyboardIOS(testCase.getMobileDriver(), "Done");
						//testCase.getMobileDriver().scrollToExact("Base Station");
						while(MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",30)){
							Keyword.ReportStep_Pass(testCase, "waiting for the updating progress to disappear");
							MobileUtils.isMobElementExists(fieldObjects, testCase, "UpdatingProgress",15);
						}
					}
					MobileUtils.hideKeyboard(testCase.getMobileDriver(), "After renaming ");
					MobileUtils.clickOnElement(fieldObjects, testCase, "NavigateUp");
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
		}catch(Exception e){
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Exception"+e.getMessage());
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() throws KeywordException {
		return flag;
	}
}
