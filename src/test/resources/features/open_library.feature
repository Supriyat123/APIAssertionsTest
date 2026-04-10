Feature: Open Library Author API

  Scenario: Validate author details from the API
    Given I send a GET request to the Open Library author endpoint
    Then the response status code should be 200
    And the personal_name should be "Sachi Rautroy"
    And the alternate_names should contain "Yugashrashta Sachi Routray"