Feature: Relationships in Objects should be exposed in GraphQL as nested fields
  # https://issues.liferay.com/browse/LPS-141968


  Background:
    Given active object definitions created
      | name       | en_US_label | en_US_plural_label | requiredStringField |
      | Student    | Student     | Students           | firstname           |
      | Subject    | Subject     | Subjects           | name                |
      | University | University  | Universities       | name                |


    Given a relationship between two object definitions created
      | name        | studentSubjects |
      | en_US_label | studentSubjects |
      | type        | oneToMany       |
      | object1name | Student         |
      | object2name | Subject         |


  Scenario: Relationships in Objects exposed in GraphQL as nested fields
    Given a relationship between two object definitions created
      | name        | subjectUniversities |
      | en_US_label | subjectUniversities |
      | type        | oneToMany           |
      | object1name | Subject             |
      | object2name | University          |
