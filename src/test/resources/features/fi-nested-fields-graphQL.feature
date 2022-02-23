Feature: Relationships in Objects should be exposed in GraphQL as nested fields
  # https://issues.liferay.com/browse/LPS-141968


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

  Scenario: Relationships in Objects exposed in GraphQL as nested fields
    Given manager accounts created
      | firstname |
      | Alex      |
      | Greta     |
    Given employee accounts created
      | firstname | supervisor |
      | Jack      | Alex       |
      | Otis      | Greta      |
    When performing a GraphQL query "{c{employees{items {firstname employeeId managerId}}}}"
    Then the data is being delivered correctly
      | employee | manager |
      | Jack     | Alex    |
      | Otis     | Greta   |