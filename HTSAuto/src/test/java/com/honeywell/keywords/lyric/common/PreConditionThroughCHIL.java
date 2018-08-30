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

public class PreConditionThroughCHIL extends Keyword {

	private TestCases testCase;
	private TestCaseInputs inputs;
	public ArrayList<String> parameters;
	public boolean flag = true;

	public PreConditionThroughCHIL(TestCases testCase, TestCaseInputs inputs, ArrayList<String> parameters) {
		this.testCase = testCase;
		this.inputs = inputs;
		this.parameters=parameters;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() throws KeywordException {
		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^(.*) as precondition$")
	public boolean keywordSteps() throws KeywordException {
		try {
			@SuppressWarnings("resource")
			ChapiUtil chUtil = new ChapiUtil(inputs);
			LocationInformation locInfo = new LocationInformation(testCase, inputs);
			ThermostatInformation statInfo = new ThermostatInformation(testCase, inputs);
			if (chUtil.getConnection()) {
				switch (parameters.get(0).toUpperCase()) {
				case "HOME MODE":{
					int result = chUtil.setBaseStationMode(locInfo.getLocationID(), statInfo.getDeviceID(),"Home");
					if (result == 202) {
						Keyword.ReportStep_Pass(testCase, "Base station is set to home");
					} else {
						flag = false;
						Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
								"Could not set base station in home : " + result);
					}
					break;
				}
				case "HOMECAMERA OFF":{
					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"not implemented");
					break;
				}
				default:{

					flag = false;
					Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE,
							"FInput not handled " + parameters.get(0).toUpperCase());

				}
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
