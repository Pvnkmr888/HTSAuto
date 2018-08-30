@DASDIY
Feature: DAS DIY
As a user I want to register a DAS device using the Lyric application

  @DIYFlow @Automated
  Scenario Outline: As a user I want to register a DAS device using the Lyric application
    Given user DAS device with ADB ID "71becea8" is deregistered and booted
      And user logs in to Lyric app
     When user clicks on "Add Device" button
     Then user should be displayed with the "select device" screen
     When user selects "Honeywell DAS" device from install device screen
     Then user should be displayed with the "location selection" screen
     When user clicks on <location name> button
     Then user should be displayed with the "DAS Name" screen
     When user clicks on <device name> button
     Then user should receive a "quick start guide" pop up
     When user clicks on "close" button
     Then user should be displayed with the "power base station" screen
     When user clicks on "next" button
     Then user should be displayed with the "power base station instructions" screen
     When user clicks on "next" button
     Then user should be displayed with the "select base station" screen
     When user selects a base station with MAC ID "B8:2C:A0:00:0A:79"
     Then user should be displayed with the "register base station" screen
     When user scans the QR code by showing it to the base station camera
     Then user should be displayed with the "Connect to Network" screen
     When user selects "TP-LINK_POCKET_3020_6F0846" WiFi
      And user inputs "93670913" as the WiFi Password
      And user clicks on "Join" button
     Then user should be displayed with a setup progress bar
      And user should be displayed with the "setup complete" screen
     When user clicks on "No" button
     Then user should be displayed with <device name> DAS device on dashboard
      And user deletes <device name> device from <location name> location through CHIL
  
    Examples: 
      | location name | device name | 
      | Home          | My Apollo   | 
      
    @DASDIYQRCodePopUpVerification
	Scenario Outline: As a user I want to retry QR code scanning when registering my DAS device
    Given user DAS device with ADB ID "71becea8" is deregistered and booted
      And user logs in to Lyric app
     When user clicks on "Add Device" button
     Then user should be displayed with the "select device" screen
     When user selects "Honeywell DAS" device from install device screen
     Then user should be displayed with the "location selection" screen
     When user clicks on <location name> button
     Then user should be displayed with the "DAS Name" screen
     When user clicks on <device name> button
     Then user should receive a "quick start guide" pop up
     When user clicks on "close" button
     Then user should be displayed with the "power base station" screen
     When user clicks on "next" button
     Then user should be displayed with the "power base station instructions" screen
     When user clicks on "next" button
     Then user should be displayed with the "select base station" screen
     When user selects a base station with MAC ID "B8:2C:A0:00:0A:79"
     Then user should be displayed with the "register base station" screen
     When QR code is not scanned for "2" minutes
     Then user should receive a "scanning failure" pop up
     When user clicks on "retry" button
     And user scans the QR code by showing it to the base station camera
     Then user should be displayed with the "Connect to Network" screen
     When user selects "TP-LINK_POCKET_3020_6F0846" WiFi
      And user inputs "93670913" as the WiFi Password
      And user clicks on "Join" button
     Then user should be displayed with a setup progress bar
      And user should be displayed with the "setup complete" screen
     When user clicks on "No" button
     Then user should be displayed with <device name> DAS device on dashboard
      And user deletes <device name> device from <location name> location through CHIL 
     
       Examples: 
      | location name | device name | 
      | Home          | My Apollo   | 