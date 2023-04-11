@advanced-object-apis
Feature: Extend Object APIs with advanced features
  # https://issues.liferay.com/browse/LPS-134171

  Background:
    Given active object definitions created
      | name       | en_US_label | en_US_plural_label | requiredStringField |
      | University | University  | Universities       | name                |
      | Subject    | Subject     | Subjects           | name                |
      | Student    | Student     | Students           | name                |


  Scenario: Create bunch of relationships
    Given a relationship between two object definitions created
      | name        | studentSubjects |
      | en_US_label | studentSubjects |
      | type        | oneToMany   |
      | object1name | Student     |
      | object2name | Subject     |
    Given a relationship between two object definitions created
      | name        | subjectUniversities |
      | en_US_label | subjectUniversities |
      | type        | oneToMany          |
      | object1name | Subject            |
      | object2name | University         |
    Given a relationship between two object definitions created
      | name        | universityStudents |
      | en_US_label | universityStudents |
      | type        | oneToMany             |
      | object1name | University            |
      | object2name | Student               |
    Given a relationship between two object definitions created
      | name        | studentsUniversities |
      | en_US_label | studentsUniversities |
      | type        | manyToMany       |
      | object1name | Student          |
      | object2name | University       |
    Given a relationship between two object definitions created
      | name        | subjectsStudents |
      | en_US_label | subjectsStudents  |
      | type        | manyToMany        |
      | object1name | Subject           |
      | object2name | Student           |
    Given a relationship between two object definitions created
      | name        | universitiesSubjects  |
      | en_US_label | universitiesSubjects  |
      | type        | manyToMany |
      | object1name | University |
      | object2name | Subject    |

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