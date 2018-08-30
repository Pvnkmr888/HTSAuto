package com.honeywell.lyric.utils;

public class GlobalVariables {
    
    public static final String LOCATION_SERVICES = "adb shell settings put secure location_providers_allowed network";
    
    public static String forceStopApp="adb shell am force-stop com.honeywell.acs.startapp";

    public static final String TestDataPath = "src/test/resources/TestDataFolder/Test_Data/";
    
    public static String FAHRENHEIT="Fahrenheit";
    
    public static final String CELSIUS = "Celsius"; 
}
