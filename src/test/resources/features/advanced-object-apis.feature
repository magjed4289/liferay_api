Feature: Extend Object APIs with advanced features
  # https://issues.liferay.com/browse/LPS-134171

  Background:
    Given active object definitions created
      | name     | en_US_label | en_US_plural_label | requiredStringField |
      | Employee | Employee    | Employees          | firstname           |
      | Manager  | Manager     | Managers           | firstname           |
    Given a relationship between two object definitions created
      | name        | supervisor |
      | en_US_label | supervisor |
      | type        | oneToMany  |
      | object1name | Manager    |
      | object2name | Employee   |

  Scenario: Nested fields in oneToOne relationship
    Given manager accounts created
      | firstname |
      | Alex      |
      | Greta     |
    Given employee accounts created
      | firstname | supervisor |
      | Jack      | Alex       |
      | Otis      | Greta      |
    When calling for "employees" with "manager" as nestedfields parameter
    Then "employee" information should be provided with "manager" information nested as an object
      | employee | manager |
      | Jack     | Alex    |
      | Otis     | Greta   |