package com.honeywell.keywords.lyric.das;
import java.util.ArrayList;
import java.util.HashMap;
import org.openqa.selenium.Dimension;
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

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class VerifySenstivityLevel extends Keyword {
	
	public boolean flag =true;
	public TestCases testCase;
    public TestCaseInputs inputs;
	private ArrayList<String> expectedLocator;



     public VerifySenstivityLevel(TestCases testCase, TestCaseInputs inputs,ArrayList<String> expectedLocator) {
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
    @KeywordStep(gherkins ="^verify (.*) is displayed (.*)$")
    public boolean keywordSteps() throws KeywordException 
	{
		HashMap<String, MobileObject> fieldObjects  = MobileUtils.loadObjectFile(testCase, "Das_Settings");
		AppiumDriver<MobileElement> driver = testCase.getMobileDriver();
		Dimension dimension = driver.manage().window().getSize();
		TouchAction touchAction = new TouchAction(testCase.getMobileDriver());
		int i= 0;
		while ((!MobileUtils.isMobElementExists(fieldObjects, testCase, "MotionDetection_LowSensitivityDesc", false)) && i<10){
		touchAction.press(10, (int) (dimension.getHeight() * .5))
			.moveTo(0, (int) (dimension.getHeight() * -.4)).release().perform();
		i++;
		
		switch (expectedLocator.get(0).toUpperCase()) {
		
		case "ZONE1":
        {
         	System.out.println("entered zones case");
         	if(testCase.getPlatform().equalsIgnoreCase("Android"))
         	{
         	if(MobileUtils.isMobElementExists(fieldObjects, testCase, "MotionDetection_LowSensitivityText")){
    			Keyword.ReportStep_Pass(testCase,"MotionDetection LowSensitivityText is displayed");
    		}else{
    			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "MotionDetection LowSensitivityText is not displayed");
    		}
         	}
         	
    		if(MobileUtils.isMobElementExists(fieldObjects, testCase, "MotionDetection_LowSensitivityDesc")){
    			/*displayedValue=MobileUtils.getMobElement(fieldObjects, testCase, "MotionDetection_LowSensitivity").getAttribute("name");
    			if(displayedValue.toLowerCase().contains(expectedZone.trim())){
    			 */Keyword.ReportStep_Pass(testCase,"MotionDetection LowSensitivity  is displayed");
    			 /*}else{
    				Keyword.ReportStep_Fail(testCase, FailType.FRAMEWORK_CONFIGURATION, "MotionDetection LowSensitivity zone mismatched expecetd Zone is "+expectedZone+" but found "+displayedValue);
    			}*/
    		}else{
    			Keyword.ReportStep_Fail(testCase, FailType.FRAMEWORK_CONFIGURATION, "MotionDetection LowSensitivity is not displayed");
    		}
			

         	
         	break;
         }
        	
		default: {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					expectedLocator.get(0) + " input not handled");
		}
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
