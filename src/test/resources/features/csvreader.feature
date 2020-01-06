Feature: Testing a REST API , Users should be able to submit GET and POST requests to a web service.

      Scenario: Post csv data
        Given I set POST data API service
        When I set request HEADER
        And send a POST HTTP request
        Then I receive a valid Response
      Scenario: Get csv data
        Given I set GET data API service
        When I set request HEADER
        And send a GET HTTP request
        Then I receive a valid HTTP Response code 200