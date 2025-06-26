Feature: Login validation for different users

  Scenario Outline: Attempt login with different user accounts
    Given I am on the login page
    When I enter username "<username>" and password "<password>"
    And I click on the login button
    Then the login result should be correct for "<username>"

    Examples:
      | username         | password      |
      | standard_user    | secret_sauce  |
      | locked_out_user  | secret_sauce  |
      | problem_user     | secret_sauce  |
