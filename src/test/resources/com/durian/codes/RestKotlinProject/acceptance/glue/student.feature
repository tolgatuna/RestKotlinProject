Feature: Student Feature

  Scenario: client makes call to GET student list
    Given There are students have been already saved to database
    When the client makes call to student list service
    Then the client should receive student list

  Scenario: client makes call to GET a specific student
    Given There are students have been already saved to database
    When the client makes call to get student service with student number
    Then the client should receive requested student

  Scenario: client makes call to create (POST) new student
    When the client makes call to create student service with correct request
    Then the client should successfully create a student

  Scenario: client makes call to update (PUT) a student
    Given There are students have been already saved to database
    When the client makes call to update student service with student number
    Then the client should update student

  Scenario: client makes call to delete (DELETE) a student
    Given There are students have been already saved to database
    When the client makes call to delete student service with student number
    Then the client should delete specified student
