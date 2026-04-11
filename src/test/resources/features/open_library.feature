Feature: Open Library Author API

 Scenario Outline: Validate author details from the API
    Given I send a GET request to the Open Library author endpoint
    Then the response status code should be <status_code>
    And the personal_name should be "<personal_name>"
    And the alternate_names should contain "<alternate_name>"
  Examples:
      |personal_name|alternate_name|status_code|
      |Sachi Rautroy|Yugashrashta Sachi Routray|200|

