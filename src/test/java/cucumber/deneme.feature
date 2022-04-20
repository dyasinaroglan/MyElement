Feature:  deneme selenide

  Scenario: deneme scenario
    Given user on homepage
    And user clicks My accpunt
    And user clicks loginLink
    When user fill the form as follows
      | username | testngkurs@gmail.com |
      | password | testngkurs           |
    And user clicks submit button
    Then login should be successful