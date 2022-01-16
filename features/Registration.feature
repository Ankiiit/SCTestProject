Feature: Page Registration

  @Smoke
  Scenario: Successful Registration
    Given I am on Buggy cars rating homepage
    When I click on Register link button
    And enter registration details
    Then Message is Displayed “Registration is Successful”

  Scenario: Registration with existing username
    Given I am on Buggy cars rating homepage
    When I click on Register link button
    And enter registration details with existing username
    Then Message is Displayed “User Exists”

  Scenario: API Tests
    Given I am on Buggy cars rating homepage
    When I fire a get request using restAssured
    Then I get http response as OK