#language: en

@api @reqres
Feature: Testing ReqRes API

  Scenario: Retrieve the list of users from the second page and verify avatar filenames are unique
    When I send a GET request to "https://reqres.in/api/users?page=2"
    Then I receive a successful response with status code 200
    And the response contains page number 2 and total users 12
    And the number of users per page is 6
    And the list of users is not empty
    And all avatar filenames of the users are unique