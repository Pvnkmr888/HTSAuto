
package com.honeywell.keywords.lyric.das;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.FluentWait;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.report.FailType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/* =========================================Keyword description====================================================================
 * Developer : Pratik P. Lalseta [H119237]
 * 
 * Goal     : Force close and reopen the app.
 * 
 * Strategy : This keyword will close and reopen the app. 
 * 
 * INPUTS   : TestCase, TestCaseInputs
 * 
 * OUTPUT   : Boolean value indicating success(true) or failure(false) of the keyword.
 * 
 * EXAMPLE :  Keyword.execute(CloseandReOpenApp.class, testCase, inputs);
 =========================================Keyword description End here====================================================================*/
public class CloseandReOpenApp extends Keyword {
/*========================================Input declaration======================================================== */

	private TestCases testCase;
	/*================================================================================================================ */
	private boolean flag;

	public CloseandReOpenApp(TestCases testCase, TestCaseInputs inputs) {
		this.testCase = testCase;
	}
	/*========================================Before method======================================================== */
	@Override
	@BeforeKeyword
	public boolean preCondition() {

		return true;
	}
	
	/*========================================Keyword Steps======================================================== */
	 
   
	@Override
	@KeywordStep(gherkins = "^user force kill and relaunches the app$")
	public boolean keywordSteps() {
		flag = true;
		try
		{
			testCase.getMobileDriver().closeApp();
			FluentWait<AppiumDriver<MobileElement>> fWait = new FluentWait<AppiumDriver<MobileElement>>(
					testCase.getMobileDriver());
			fWait.withTimeout(10, TimeUnit.SECONDS);
			testCase.getMobileDriver().launchApp();
			Keyword.ReportStep_Pass(testCase, "Close and Reopen App : Successfully forced close the app and reopen it");
		}
		catch(Exception e)
		{
			flag=false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Close and Reopen App : Failed forced close the app and reopen it. Error occured : " + e.getMessage());
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
			if(flag){
				ReportStep_Pass(testCase, "Close and Reopen App : Successful");
			}
			else{
				ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Close and Reopen App : Failed");
			}
		return flag;
	}

}
