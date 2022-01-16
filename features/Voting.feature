Feature: Voting Feature
	@Sanity
  Scenario: Voting for car
    Given I am on website homepage
    When I fill in login details to login
    And click on the first car to vote
    Then my vote gets counted