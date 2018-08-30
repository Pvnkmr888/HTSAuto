@DASCommandAndControl
Feature: DAS Command And Control
As a user I want to change the status of my DAS device

  @CommandControl
  Scenario: As a user I want to switch to different states in my DAS device
    Given user logs in to Lyric app
      And user selects DAS device from the dashboard
     When user switches from "Home" to "Away"
     Then user should be displayed with a "Switching to Away" text
      And user should be displayed with a switching timer
     When timer ends on user device
     Then user status should be set to "Away"
     When user switches from "Away" to "Home"
     Then user should be displayed with a "Switching to Home" text
      And user status should be set to "Home"
     When user switches from "Home" to "Night"
     Then user should be displayed with a "Switching to Night" text
      And user should be displayed with a switching timer
     When timer ends on user device
     Then user status should be set to "Night"
     When user switches from "Night" to "Away"
     Then user should be displayed with a "Switching to Away" text 
      And user should be displayed with a switching timer
     When timer ends on user device
     Then user status should be set to "Away"
     When user switches from "Away" to "Night"
     Then user should be displayed with a "Switching to Night" text
      And user should be displayed with a switching timer
     When timer ends on user device
     Then user status should be set to "Night"
     When user switches from "Night" to "Home"
     Then user should be displayed with a "Switching to Home" text
      And user status should be set to "Home"
      