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

  Scenario: Nested fields in oneToMany relationship
    Given manager accounts created
      | firstname |
      | Alex      |
      | Greta     |
    Given employee accounts created
      | firstname | supervisor |
      | Jack      | Alex       |
      | Otis      | Greta      |
    When calling for employees with "manager" as nestedFields parameter
    Then "employee" information should be provided with "manager" information nested as an object
      | employee | manager |
      | Jack     | Alex    |
      | Otis     | Greta   |

  Scenario: Nested fields in oneToMany relationship after updating a relationship
    Given manager accounts created
      | firstname |
      | Alex      |
      | Greta     |
    Given employee accounts created
      | firstname | supervisor |
      | Jack      | Alex       |
      | Otis      | Greta      |
    Given employee accounts updated
      | firstname | supervisor |
      | Jack      | Greta      |
      | Otis      | Alex       |
    When calling for employees with "manager" as nestedFields parameter
    Then "employee" information should be provided with "manager" information nested as an object
      | employee | manager |
      | Jack     | Greta   |
      | Otis     | Alex    |

  Scenario: Nested fields in oneToMany relationship after deleting one of the objects involved
    Given manager accounts created
      | firstname |
      | Alex      |
      | Greta     |
    Given employee accounts created
      | firstname | supervisor |
      | Jack      | Alex       |
      | Otis      | Greta      |
    Given deleting one of the employees
      | firstname | supervisor |
      | Otis      | Greta      |
    When calling for employees with "manager" as nestedFields parameter
    Then "employee" information should be provided with "manager" information nested as an object
      | employee | manager |
      | Jack     | Alex    |

  Scenario: Aggregation terms in oneToMany relationship
    Given manager accounts created
      | firstname |
      | Alex      |
      | Greta     |
    Given employee accounts created
      | firstname | supervisor |
      | Jack      | Alex       |
      | Otis      | Greta      |
      | Eugene    | Greta      |
    When calling for employees with "managerId" as aggregationTerms parameter
    Then I receive the information about the aggregationTerm as facets of the response
      | numberOfOccurrences | relatedManagerFirstname |
      | 2                   | Greta                   |
      | 1                   | Alex                    |

  Scenario: Aggregation terms in oneToMany relationship - relationship updated
    Given manager accounts created
      | firstname |
      | Alex      |
      | Greta     |
    Given employee accounts created
      | firstname | supervisor |
      | Jack      | Alex       |
      | Otis      | Greta      |
      | Eugene    | Greta      |
    Given employee accounts updated
      | firstname | supervisor |
      | Jack      | Greta      |
    When calling for employees with "managerId" as aggregationTerms parameter
    Then I receive the information about the aggregationTerm as facets of the response
      | numberOfOccurrences | relatedManagerFirstname |
      | 3                   | Greta                   |

  Scenario: Aggregation terms in oneToMany relationship - related object deleted
    Given manager accounts created
      | firstname |
      | Alex      |
      | Greta     |
    Given employee accounts created
      | firstname | supervisor |
      | Jack      | Alex       |
      | Otis      | Greta      |
      | Eugene    | Greta      |
    Given deleting one of the employees
      | firstname | supervisor |
      | Otis      | Greta      |
    When calling for employees with "managerId" as aggregationTerms parameter
    Then I receive the information about the aggregationTerm as facets of the response
      | numberOfOccurrences | relatedManagerFirstname |
      | 1                   | Greta                   |
      | 1                   | Alex                    |