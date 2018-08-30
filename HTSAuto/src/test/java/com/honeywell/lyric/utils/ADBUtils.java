package com.honeywell.lyric.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.honeywell.commons.coreframework.SuiteConstants;
import com.honeywell.commons.coreframework.SuiteConstants.SuiteConstantTypes;

public class ADBUtils {
	
	public static String executeADBCommand(String cmd) throws Exception {
		String output = " ";
		try {
			String[] tempArr = cmd.split(" ");
			String[] cmdArray;
			List<String> cmdList = new ArrayList<String>();
			cmdList.add(SuiteConstants.getConstantValue(SuiteConstantTypes.PROJECT_SPECIFIC, "LOCAL_ADB_PATH"));
			for (int i = 1; i < tempArr.length; i++) {
				cmdList.add(tempArr[i]);
			}
			cmdArray = cmdList.toArray(new String[cmdList.size()]);
			int n = tempArr.length - 1;
			String[] commandArr = new String[n];
			System.arraycopy(tempArr, 1, commandArr, 0, n);
			Runtime run = Runtime.getRuntime();
			Process pr = null;

			// pr = run.exec(new
			// String[]{"/usr/local/Cellar/android-sdk/24.4.1_1/bin/adb"},commandArr);
			pr = run.exec(cmdArray);
			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String temp;
			while ((temp = buf.readLine()) != null)
				output += temp;
		} catch (IOException e) {
			throw new Exception("IO Exception caused by " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Error Occured: " + e.getMessage());
		}
		return output;
	}
	
	public static String[] getAllADBDevices() throws Exception
	{
		String[] devices;
		String adbDevices = ADBUtils.executeADBCommand("adb devices");
		adbDevices = adbDevices.replace("List of devices attached", "");
		devices = adbDevices.split("\\t");
		for(int i=0;i<devices.length;i++)
		{
			devices[i] = devices[i].trim();
			if(devices[i].equals("device"))
			{
				devices = ArrayUtils.remove(devices, i);
				continue;
			}
			if(devices[i].contains("device"))
			{
				devices[i] = devices[i].replace("device", "");
			}
		}
		return devices;
	}
	
	public static boolean rebootDASDevice(String dasDeviceADBID) throws Exception
	{
		boolean flag = true;
		String[] devices = ADBUtils.getAllADBDevices();
		boolean isDASDeviceConnected = false;
		for(int i=0; i<devices.length;i++)
		{
			if(devices[i].equals(dasDeviceADBID))
			{
				isDASDeviceConnected = true;
				break;
			}
		}
		if(isDASDeviceConnected)
		{
			String output = ADBUtils.executeADBCommand("adb -s " + dasDeviceADBID + " reboot");
			if(!output.trim().equals(""))
			{
				flag = false;
				throw new Exception("Could not reboot device. Output : " + output);
			}
		}
		else
		{
			throw new Exception("DAS device '" + dasDeviceADBID + "' is not connected. DAS device could not be found in ADB devices command");
		}
		return flag;
	}
	
	public static boolean deregisterDASDevice(String dasDeviceADBID) throws Exception
	{
		boolean flag=true;
		String[] devices = ADBUtils.getAllADBDevices();
		boolean isDASDeviceConnected = false;
		for(int i=0; i<devices.length;i++)
		{
			if(devices[i].equals(dasDeviceADBID))
			{
				isDASDeviceConnected = true;
				break;
			}
		}
		if(isDASDeviceConnected)
		{
			String output = ADBUtils.executeADBCommand("adb -s " + dasDeviceADBID + " shell /honeywell/app/e2c/device_delete.sh");
			if(!output.trim().equals(""))
			{
				flag = false;
				//throw new Exception("Could not deregister DAS device. Output : " + output);
			}
		}
		else
		{
			throw new Exception("DAS device '" + dasDeviceADBID + "' is not connected. DAS device could not be found in ADB devices command");
		}
		return flag;
	}
	
}
