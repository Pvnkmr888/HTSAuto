package com.honeywell.keywords.lyric.common;

import java.util.ArrayList;

import com.honeywell.chapi.ChapiUtil;
import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordException;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.report.FailType;
import com.honeywell.stat.LocationInformation;
import com.honeywell.stat.ThermostatInformation;

public class DeleteDeviceThroughCHIL extends Keyword {

	private TestCases testCase;
	private TestCaseInputs inputs;
	public ArrayList<String> parameters;
	public boolean flag = true;

	public DeleteDeviceThroughCHIL(TestCases testCase, TestCaseInputs inputs, ArrayList<String> parameters) {
		this.testCase = testCase;
		this.inputs = inputs;
		this.parameters = parameters;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() throws KeywordException {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user deletes (.*) device from (.*) location through CHIL$")
	public boolean keywordSteps() throws KeywordException {
		try {
			@SuppressWarnings("resource")
			ChapiUtil chUtil = new ChapiUtil(inputs);
			inputs.setInputValue("LOCATION1_NAME", parameters.get(1), false);
			inputs.setInputValue("LOCATION1_DEVICE1_NAME", parameters.get(0), false);
			LocationInformation locInfo = new LocationInformation(testCase, inputs);
			ThermostatInformation statInfo = new ThermostatInformation(testCase, inputs);
			if (chUtil.getConnection()) {
				int result = chUtil.deleteDevice(locInfo.getLocationID(), statInfo.getDeviceID());
				if (result == 200) {
					Keyword.ReportStep_Pass(testCase, "Successfully deleted DAS Device");
				} else {
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"Failed to delete DAS device. Response Code : " + result);
				}
			}
		} catch (Exception e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured : " + e.getMessage());
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() throws KeywordException {
		return flag;
	}
}
