Feature: DAS Camera Settings
  As user
  I should be able Controll camera settings 
  so that I can control DAS camera settings through mobile app


Background: 
#Given "Home Mode" as precondition

  @dasCameraSettings_Options
  Scenario Outline: As a user I want to verify that all DAS Settings options are available to me
    Given user logs in to Camera app
    And "CAMERA MODE ON" as precondition
    When user is in Security primary card
    And user clicks on "Menu Icon"
    And user clicks on "Das Device"
    Then verify the display of following <elements>

    Examples: 
      | elements         |
      | Home             |
      | Away             |
      | Sleep            |
      | Entry/Exit delay |
      | Keyfob           |
      | Alexa            |
      | Base station     |
      | Sensors          |

  @dasSecurityStatusSettings_Option
  Scenario Outline: As a user I want to verify that HOME/Away/Sleep settings options are available
    Given user logs in to Camera app
    And "CAMERA MODE ON" as precondition
    When user is in Security primary card
    And user clicks on "Menu Icon"
    And user clicks on "Das Device"
    And user clicks on <Security Status>
    Then Verify the "Alarm" is displayed
    And Verify the "Base Station Camera" is displayed
    And Verify the "Chime Valume" is displayed

    Examples: 
      | Security Status |
      | Home            |
      | Away            |
      | Sleep           |

  @DasHomeSettings @UIAutomated
  Scenario Outline: As a user I want to verify if Home status can be changed to my priority
    Given user logs in to Lyric app
    When user clicks on "Hamburger menu" button
    And user clicks on "DAS Device" menu
    And user clicks on "Home settings" menu
    Then "Home description with icon" is displayed on settings screen
    And "HomeAlarm is always Off" with description
    When user <toggle> on "HomeCamera"
    And user clicks on "Home settings" menu
    Then verify "HomeCamera" button is <expectedState>
    Then verify changed "chimevolume" is updated for "Home settings"

    Examples: 
      | toggle | expectedState |
      | enable | enabled       |
  #   | disable | disabled     |
  
  @DasAwaySettings @UIAutomated
  Scenario: As a user I want to verify if Away status can be changed to my priority
    #Given "AwayCamera" as <precondition>
    Given user logs in to Lyric app
    When user clicks on "Hamburger menu" button
    And user clicks on "DAS Device" menu
    And user clicks on "Away settings" menu
    Then "Away description with icon" is displayed on settings screen
    Then "AwayAlarm is always On" with description
    And "AwayCamera is always On" with description
    Then verify changed "chimevolume" is updated for "Away settings"

  @DasNightSettings @UIAutomated
  Scenario Outline: As a user I want to verify if Night status can be changed to my priority
    
    #  Given "NightCamera OFF" as precondition
    Given user logs in to Lyric app
    When user clicks on "Hamburger menu" button
    And user clicks on "DAS Device" menu
    And user clicks on "Night settings" menu
    Then "NightAlarm is always On" with description
    When user <toggle> on "NightCamera"
     And user clicks on "Night settings" menu
    Then verify "NightCamera" button is <expectedState>
    Then verify changed "chimevolume" is updated for "Night settings"

    Examples: 
      | toggle | expectedState |
      | enable | enabled       |
  #   | disable | disabled      |
  
  
   @DasEntryExitSettings
  Scenario: DasEntryExitSettings
    As user I want to verify if entry exit delay time displayed on settings and user can update the value
    Given user logs in to Lyric app
    When user clicks on "Hamburger menu" button
    And user clicks on "DAS Device" menu
    When user clicks on "Exit Entry delay" menu
    Then verify changed "delayValue" is updated for "Settings"

  #  Examples:
  #   | delayValue |
  #   |          0 |
  #   |         15 |
  #   |         30 |
  #   |         45 |
  #   |         60 |
  
  @dasChimeSettings 
  Scenario Outline: As a user i want to control Chime valume using Camera settings
    Given user is in "Das Device Settings"
    When user clicks on <Security settings>
    Then verify changed "delayValue" is updated for "Settings"
    Then verify Appropraite <Chime Valume> is displayed

    Examples: 
      | Security settings | Chime Valume |
      | Home              |           50 |
      | Away              |           50 |
      | Sleep             |           50 |

  @dasBasestationRename
  Scenario: As a user i want to rename my Base station
    Given user logs in to Lyric app
    When user clicks on "Hamburger menu" button
    And user clicks on "DAS Device" menu
    When user clicks on "Base station settings" menu
    Then verify changed "NAME" is updated for "BASESTATION"

  @dasKeyfabaccess
  Scenario: As a user I want to verify Keyfob settings can be accesed
    Given user is "Das Device Settings"
    When user clicks on "KeyFob settings"
    Then verify keyfab name is displayed
    And Verify exisisting Keyfob entry is displayed
    And verify list of other invited lyric users are displayed
    And verify "Delete option" is displayed

  @dasKeyfabReassign
  Scenario: As a user I want to verify Keyfob settings can be reassigned
    Given user is "Das Device Settings"
    When user clicks on "KeyFob settings"
    And Keyfab is assigned to exisintg user
    And user re-assigned the Keyfab to other user
    Then verify re-assigned user is selected

  @dasKeyfabRename
  Scenario: As a user i want to rename my keyfab
    Given user is "Das Device Settings"
    When user clicks on "KeyFob settings"
    And User clicks on "camera name"
    Then verify user can clear text
    When user renames the camera
    Then verify renamed camera name persists

  @dasKeyfabDelete
  Scenario: As a user I want to Delete by keyfab
    Given user is "Das Device Settings"
    When user clicks on "KeyFob settings"
    And user clicks on "Delete"
    Then verify keyfab is delete fron the User account

  @dasKeyFabUsersscroll
  Scenario: As a user I want to scroll between the Multiple users to assign an Keyfab
    Given user is "Das Device Settings"
    When user clicks on "KeyFob settings"
    And user jhas multiple invited user (More then <3)
    Then Verify User can scroll through the users list

  @dasBaseStationSettings
  Scenario Outline: As a user I want to verify that all DAS Settings options are available to me
    Given user is "Das Device Settings"
    When user clicks on "Base station Settings"
    Then verify the display of following <elements>

    Examples: 
      | elements           |
      | Base station Name  |
      | Wifi               |
      | Battery            |
      | Camera             |
      | Model and Firmwarw |
      | Delete             |

  @dasBasestationWifirange
  Scenario: As a user i want to know my Wifi strength of Base station
    a user i want to rename my Base station

    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    Then Verify Base station Wifi Strength is displayed appropriately

  @dasBasestationBatterystatus
  Scenario: As a user i want to know base station camera status of Base station
    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    Then Verify Base station camera battery status is displayed appropriately

  @dasBasestationModelandFirmware
  Scenario Outline: As a user I want to verify that all Model and Firmware details are available to me
    Given user is in "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Model and Firmware settings"
    Then verify the display of following:

    Examples: 
      | Elements              |
      | Model Details         |
      | MAC id :              |
      | Firmware details      |
      | Firmware version      |
      | Firmware Last updated |

  @dasBasestationCameraSettingsAndMicrophone @Automated
  Scenario: As a user I want to verify that all Camera setting options
    Given user logs in to Lyric app
    When user clicks on "HAMBURGER MENU" button
    And user clicks on "DAS DEVICE" menu
    And user clicks on "BASE STATION SETTINGS" menu
    And user clicks on "CAMERA SETTINGS" menu
    Then verify the display of following:
      | Elements         |
      | MOTION DETECTION |
      | MICRO PHONE      |
      | NIGHT VISION     |
      | VIDEO QUALITY    |
    When user enables "CAMERA MICROPHONE"
    Then verify "CAMERA MICROPHONE" is enabled
    When user disables "CAMERA MICROPHONE"
    Then verify "CAMERA MICROPHONE" is disabled

  @dascameraMicrophone_sharedLogin
  Scenario: CameraMicrophone_On
    As a user I want to verify that I can turning on camera microphone.

    Given user is in "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    When user enables "Camera Microphone"
    Then verify "camera microphone" is "enabled"

  Scenario: CameraMicrophone_On
    As a user I want to verify that I can turning Off camera microphone.

    Given user is in "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    When user disables "Camera Microphone"
    Then verify "camera microphone" is "Disable"

  @CameraNightVision_Menu @Automated @nightVision_sharedLogin
  Scenario: CameraNightVision_Menu
    As a user I want to verify the Night vision setting menu options.

    Given user logs in to Lyric app
    When user clicks on "HAMBURGER MENU" button
    And user clicks on "DAS DEVICE" menu
    And user clicks on "BASE STATION SETTINGS" menu
    And user clicks on "CAMERA SETTINGS" menu
    And user clicks on "NIGHT VISION SETTINGS" menu
    Then verify the display of following:
      | Elements          |
      | NIGHT VISION AUTO |
      | NIGHT VISION ON   |
      | NIGHT VISION OFF  |
    When user taps on "NIGHT VISION ON"
    Then verify "NIGHT VISION ON" is selected
    When user taps on "NIGHT VISION OFF"
    Then verify "NIGHT VISION OFF" is selected
    When user taps on "NIGHT VISION AUTO"
    Then verify "NIGHT VISION AUTO" is selected

  @CameraNightVision_OnSelected @nightVision_sharedLogin
  Scenario: CameraNightVision_OnSelected
    As a user I want to verify the selection of On option for Night vision setting

    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user taps on "Night vision setting"
    And user taps on "Night Vision ON"
    Then verify "Night Vision ON text" is displayed
    And user taps on "Night vision setting"
    And verify "Night Vision ON" is "selected"

  @CameraNightVision_OffSelected @nightVision_sharedLogin
  Scenario: CameraNightVision_OffSelected
    As a user I want to verify the selection of Off option for Night vision setting

    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user taps on "Night vision setting"
    And user taps on "Night Vision OFF"
    Then verify "Night Vision OFF text" is displayed
    And user taps on "Night vision setting"
    And verify "Night Vision OFF" is "selected"

  @CameraNightVision_AutoSelected @nightVision_sharedLogin
  Scenario: CameraNightVision_AutoSelected
    As a user I want to verify the selection of Auto option for Night vision setting

    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user taps on "Night vision setting"
    And user taps on "Night Vision AUTO"
    Then verify "Night Vision AUTO text" is displayed
    And user taps on "Night vision setting"
    And verify "Night Vision AUTO" is "selected"

  @videoQualitySettingMenu @Automated
  Scenario: As a user I want to verify the Video Quality setting menu options.
    Given user logs in to Lyric app
    When user clicks on "HAMBURGER MENU" button
    And user clicks on "DAS DEVICE" menu
    And user clicks on "BASE STATION SETTINGS" menu
    And user clicks on "CAMERA SETTINGS" menu
    And user clicks on "VIDEO QUALITY SETTINGS" menu
    Then verify the display of following:
      | Elements           |
      | VIDEO QUALITY AUTO |
      | VIDEO QUALITY LOW  |
      | VIDEO QUALITY HIGH |
    And user taps on "VIDEO QUALITY LOW"
    Then verify "VIDEO QUALITY LOW" is selected
    When user taps on "VIDEO QUALITY HIGH"
    Then verify "VIDEO QUALITY HIGH" is selected
    When user taps on "VIDEO QUALITY AUTO"
    Then verify "VIDEO QUALITY AUTO" is selected

  @videoQualitySettingAutoSelected
  Scenario: As a user I want to verify the selection of Auto option for Video Quality setting
    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user taps on "Video Quality setting"
    And user taps on "Video Quality AUTO"
    Then verify "Video Quality AUTO" is "selected"
    Then verify "Video Quality Auto text" is displayed

  @videoQualitySettingLowSelected
  Scenario: As a user I want to verify the selection of Low option for Video Quality setting
    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user taps on "Video Quality setting"
    And user taps on "Video Quality LOW"
    Then verify "Video Quality LOW" is "selected"
    Then verify "Video Quality Low text" is displayed

  @videoQualitySettingHighSelected
  Scenario: As a user I want to verify the selection of High option for Video Quality setting
    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user taps on "Video Quality setting"
    And user taps on "Video Quality HIGH"
    Then verify "Video Quality HIGH" is "selected"
    Then verify "Video Quality High text" is displayed

  Scenario: 
    As a user i want to verify if motion detection is disabled when user disables motion detection

    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user clicks on "Motion Detection"
    When user disables "Motion Detection button"
    Then verify "Motion Detection" is "Disabled"
    And verify "motion detection value off" is displayed

  @motionDetection_verifyZoneSensitivityLevel
  Scenario:motionDetection_verifyZonesSensitivityLevel
    As a user i want to verify Each zones has LOW,Normal,High sensitivity Level
    Given user logs in to Lyric app
    When user clicks on "HAMBURGER MENU" button
    And user clicks on "DAS DEVICE" menu
    And user clicks on "BASE STATION SETTINGS" menu
    And user clicks on "CAMERA SETTINGS" menu
    And user taps on "MOTION DETECTION"    
    When user taps on "ZONE1"
    Then verify "ZONE1" is displayed "Low"

 @motionDetection_chooseZones
 Scenario:motionDetection_chooseZones
    As a user i want to verify if user can choose between the zone
    Given user logs in to Lyric app
    When user clicks on "HAMBURGER MENU" button
    And user clicks on "DAS DEVICE" menu
    And user clicks on "BASE STATION SETTINGS" menu
    And user clicks on "CAMERA SETTINGS" menu
    And user taps on "MOTION DETECTION"
    When user taps on "ZONE1"
    Then verify "ZONE1" is "selected"
    When user taps on "ZONE2"
    Then verify "ZONE2" is "selected""
    When user taps on "ZONE3"
    Then verify "ZONE3" is "selected"
    When user taps on "ZONE4"
    Then verify "ZONE4" is "selected"

    
  @motionDetection_retainZoneSelected
  Scenario:motionDetection_retainZoneSelected
    As a user i want to verify if values are retained when user chnages the settings in Motion detection
    Given user logs in to Lyric app
    When user clicks on "HAMBURGER MENU" button
    And user clicks on "DAS DEVICE" menu
    And user clicks on "BASE STATION SETTINGS" menu
    And user clicks on "CAMERA SETTINGS" menu
    And user taps on "MOTION DETECTION"
    When user taps on "ZONE4"
    And user taps on "ENABLE DETECTION ZONE"
    And verify "ENABLE DETECTION ZONE" is "selected"
    And user taps on "BACK"
    And user taps on "MOTION DETECTION"
    And user taps on "ZONE4"
    Then verify "ENABLE DETECTION ZONE" is "selected"

   

  Scenario Outline: motionDetection_verifyOtherZonesSensitivity
    As user i want to verify if user can enable detection zone for Zone2 ,Zone3 ,Zone3 and Adjust detection zone

    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user taps on "Motion Detection"
    When user taps on <Zones>
    Then verify <Zones> is "selected"
    And verify  <Zones> "Detection Zone" is "Adjustable"
    And user force kill and relaunches the app

    Examples: 
      | Zones |
      | Zone1 |
      | Zone2 |
      | zone3 |
      | Zone4 |

  Scenario: motionDetection_verifyDetectionZoneHighlight
    As user i want to verify if Detection Zone is highlight in RED when multiple zone overlap

    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user "enabled" "Motion Detection"
    And user taps on "Zone1"
    And user "Adjusts" "Zone1"
    And user taps on "Zone2"
    When user "Overlaps zone2" on "zone1"
    Then verify "Detection Zone highlight in RED"

  Scenario: motionDetection_validateErrorOnMultipleZoneOverlaps
    As user i want to verify if user get an error message when multiple zone overlaped and user navigates back

    Given user is "Das Device Settings"
    When user clicks on "Base station settings"
    And user clicks on "Camera settings"
    And user enables "Motion Detection button"
    And user taps on "Zone1"
    And user "Adjusts" "Zone1"
    And user taps on "Zone2"
    When user "Overlaps zone2" on "zone1"
    And User taps on "Back"
    Then verify "Please Arrange detection zone properly" is displayed
    And verify "OK button" is displayed

  Scenario: 
    As user I want to verify if user can set his entry/exit delay

    Given user is "Das Device Settings"
    When user clicks on "Exit/Entry delay"
    Then verify the display of following:
      | Elements |
      |       15 |
      |       30 |
      |       45 |
      |       60 |

 
  Scenario: 
    As a user I want to view my sensor settings

    Given user is in "Das Device Settings"
    And user has enrolled few sensors
    When user click on "Sensors settings"
    Then verify Each sensor are listed in Approriate sensor type
    And Verify sensor status is displayed algined with snesor name

  Scenario: 
    As a user I want to view number of sensors enrolled in Das device setting

    Given user is in "Das Device Settings"
    When user has enrolled few sensors
    Then Verify enrolled sensor count is displayed

  Scenario: 
    As a user I want to sensor details so that i can review enrolled sensors

    Given user is in "Das Device Settings"
    And user click on "Sensors settings"
    When user clicks on "Access sensor"
    Then verify the display of following:
      | Elements                   |
      | Sensor Type image          |
      | Sensor Name                |
      | ON/Off toggle              |
      | Sensor Status              |
      | Sensor signla Strength     |
      | Battery                    |
      | Select Chime               |
      | Model and Firmware Details |
      | Delete                     |

  Scenario: 
    As a user I want to sensor details so that i can review enrolled sensors

    Given user is in "Das Device Settings"
    And user click on "Sensors settings"
    When user clicks on "Motion Viewer"
    Then verify the display of following:
      | Elements                   |
      | Sensor Type image          |
      | Sensor Name                |
      | ON/Off toggle              |
      | Sensor Status              |
      | Sensor signla Strength     |
      | Battery                    |
      | Select Chime               |
      | Model and Firmware Details |
      | Delete                     |
      | Motion Sensitivity         |

  Scenario Outline: 
    As a user i want to rename my sensor using Sensor settings

    Given user is in "Das Device Settings"
    And user click on "Sensors settings"
    And user clicks on <Sensor>
    When user clicks on "Sensor text feild"
    Then verify user can clear text
    When user renames the sensor
    Then verify renamed Base station name persists

    Examples: 
      | Sensor        |
      | Access sensor |
      | Motion Viewer |

  Scenario Outline: 
    As a user i want delete my sensor from sensors setting

    Given user is "Das Device Settings"
    When user clicks on "Sensors settings"
    And user clicks on <Sensor>
    And user clicks on "Delete"
    Then verify Sensor is delete from the User account

    Examples: 
      | Sensor        |
      | Access sensor |
      | Motion Viewer |

  Scenario Outline: Verify Signal Strength Optimization
    As a user I want to know what the optimatal signal strength is to place my sensor

    Given user is "Das Device Settings"
    When user clicks on "Sensors settings"
    And user clicks on <Sensor>
    Then user should be displayed with sensor signal strength
    When user moves the sensor away-from/towards the das device
    Then signal strength should update on the application

    Examples: 
      | Sensor        |
      | Access sensor |
      | Motion Viewer |

  Scenario Outline: 
    As a user i want to verify motion sensor has LOW,Normal,High sensitivity Level

    Given user is "Das Device Settings"
    When user clicks on "Sensors settings"
    And user clicks on "Motion Viewer"
    Then verify "Motion Viewer" is displayed "Low Normal High"

  Scenario Outline: 
    As a user i want to verify access sonsor and motion viewer has chime option to selcte sound

    Given user is "Das Device Settings"
    When user clicks on "Sensors settings"
    And user clicks on <sensor>
    Then verify <sensor> has default sounds
      | Sensor        |
      | Access sensor |
      | Motion Viewer |

  Scenario Outline: 
    As a user i want to enable/disable sensor on my prefernce

    Given user is "Das Device Settings"
    When user clicks on "Sensors settings"
    And user clicks on <sensor>
    Then verify user can "Enable"
    And verify approriate Status is displayed

    Examples: 
      | Sensor        |
      | Access sensor |
      | Motion Viewer |

  Scenario Outline: 
    As a user i want to view alert message when user disbales the sensor

    Given user is "Das Device Settings"
    When user clicks on "Sensors settings"
    And user clicks on <sensor>
    Then verify user can "disable"
    And verify an alert message is displayed
    When user clicks on "OK"
    Then verify sensor is disabled
    And verify approriate Status is displayed in detail view

    Examples: 
      | Sensor        |
      | Access sensor |
      | Motion Viewer |

  Scenario Outline: 
    As a user i want to view alert message when user disbales the sensor

    Given user is "Das Device Settings"
    When user clicks on "Sensors settings"
    And user clicks on <sensor>
    Then verify user can "disable"
    And verify an alert message is displayed
    When user clicks on "Cancel"
    Then verify previous status id retained

    Examples: 
      | Sensor        |
      | Access sensor |
      | Motion Viewer |

  Scenario: 
    As a user i want to notified with an message when setting update fails

    Given user is in "Das Device Settings"
    When user updated Fails at any point
    Then Verify if Toast meesgae is displayed with Approraite error message
