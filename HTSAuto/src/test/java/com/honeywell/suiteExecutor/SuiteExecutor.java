package com.honeywell.suiteExecutor;

import com.honeywell.commons.coreframework.SuiteUtils;

public class SuiteExecutor {

	/**
	 * The Starting point of Automation Framework.
	 *
	 * @param groups
	 *            String[] array type, represents the groups to execute passed
	 *            from Command line Arguments.
	 */

	public static void main(String[] commandLineArguments) throws Exception {
		
        commandLineArguments = new String[] {"--requirementFileName","Requirement_file.txt","--useXCUITest","true","--appToInstall","Android:Lyric41,IOS:LyricLocal","--groups","motionDetection_retainZoneSelected"};        
        
        // ============= Executes the Suite created from the Suite Configuration JSON file  =============
		try {
			SuiteUtils suiteUtils = SuiteUtils.getTestSuite(commandLineArguments);
			suiteUtils.executeSuite();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
