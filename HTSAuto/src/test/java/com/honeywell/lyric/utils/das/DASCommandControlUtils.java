package com.honeywell.lyric.utils.das;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileObject;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;

public class DASCommandControlUtils {

	public static boolean changeStatus(TestCases testCase, String statusToSelect) {
		if (statusToSelect.equals("Night")) {
			statusToSelect = "Sleep";
		}
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DASSecurityCard");
		List<WebElement> statusIcons = MobileUtils.getMobElements(fieldObjects, testCase, "StatusIcons");
		boolean f = false;
		for (WebElement status : statusIcons) {
			if (status.getAttribute("name").equalsIgnoreCase(statusToSelect)) {
				status.click();
				f = true;
				break;
			}
		}
		if (f) {
			Keyword.ReportStep_Pass(testCase, "Successfully selected " + statusToSelect + "status");
		} else {
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Status " + statusToSelect + " not found");
		}
		return f;
	}

	public static boolean waitForTimerToComplete(TestCases testCase) {
		boolean flag = true;
		try {
			HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DASSecurityCard");
			if(!MobileUtils.isMobElementExists(fieldObjects, testCase, "TimerProgressBar",20))
			{
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Timer Progress Bar not displayed after waiting for 20 seconds");
				return flag;
			}
			FluentWait<String> fWait = new FluentWait<String>(" ");
			fWait.pollingEvery(3, TimeUnit.SECONDS);
			fWait.withTimeout(2, TimeUnit.MINUTES);
			Boolean isEventReceived = fWait.until(new Function<String, Boolean>() {
				public Boolean apply(String a) {
					try {
						if (MobileUtils.isMobElementExists(fieldObjects, testCase, "TimerProgressBar", 2)) {
							System.out.println("Waiting for timer to end");
							return false;
						} else {
							return true;
						}
					} catch (Exception e) {
						return false;
					}
				}
			});
			if (isEventReceived) {
				Keyword.ReportStep_Pass(testCase, "Timer ended successfully");
			}
		} catch (TimeoutException e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
					"Timer did not end even after waiting for 2 minutes");
		} catch (Exception e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
		}
		return flag;
	}
	
	public static boolean waitForProgressBarToComplete(TestCases testCase)
	{
		boolean flag = true;
		try
		{
		HashMap<String, MobileObject> fieldObjects = MobileUtils.loadObjectFile(testCase, "DASSecurityCard");
		FluentWait<String> fWait = new FluentWait<String>(" ");
		fWait.pollingEvery(3, TimeUnit.SECONDS);
		fWait.withTimeout(1, TimeUnit.MINUTES);
		Boolean isEventReceived = fWait.until(new Function<String, Boolean>() {
			public Boolean apply(String a) {
				try {
					if (MobileUtils.isMobElementExists(fieldObjects, testCase, "ProgressBar", 2)) {
						System.out.println("Waiting for progress bar to complete");
						return false;
					} else {
						return true;
					}
				} catch (Exception e) {
					return false;
				}
			}
		});
		if (isEventReceived) {
			Keyword.ReportStep_Pass(testCase, "Progress bar successfully completed");
		}
	} catch (TimeoutException e) {
		flag = false;
		Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
				"Progress bar did not complete after waiting for 1 minute");
	} catch (Exception e) {
		flag = false;
		Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
	}
		return flag;
	}

}