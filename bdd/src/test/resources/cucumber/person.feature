Feature: Example of taking the test arguments

  Scenario: Create some person
    Given some persons
|id |  email       | name  |
| 1 |  personemail | personname |
    When create person
    Then result should be equals Given and When


  Scenario: Get person
    Given some persons
|id |  email       | name  |
| 1 |  personemail | personname |
    When get person 1
    Then result should be equals Given and When

  Scenario: Update person
    Given some persons
|id |  email       | name  |
| 1 |  personemail | personname |
    When update person email 'personemail1' name 'personname1'
    Then result should be equals Given and When


  Scenario: Delete person
    Given some persons
|id |  email       | name  |
| 1 |  personemail | personname |
    When delete person 1
    Then delete result should be person is null