Feature: Objects for REST application

Background:
Given active object definitions created
| name       | en_US_label | en_US_plural_label | requiredStringField | fieldErc       | externalReferenceCode |
| University | University  | Universities       | name                | nameUniversity | University            |
| Student    | Student     | Students           | name                | nameStudent    | Student               |
| Subject    | Subject     | Subjects           | name                | nameSubject    | Subject               |
| Book       | Book        | Books              | name                | nameBook       | Book                  |
| Author     | Author      | Authors            | name                | nameAuthor     | Author                |
| Agent      | Agent       | Agents             | name                | nameAgent      | Agent                 |
| City       | City        | Cities             | name                | nameCity       | City                  |
| State      | State       | States             | name                | nameState      | State                 |


Scenario: Nested fields in oneToMany relationship
Given a relationship between two object definitions created
| name        | universityStudents |
| en_US_label | universityStudents |
| type        | oneToMany          |
| object1name | University         |
| object2name | Student            |

Given a relationship between two object definitions created
| name        | studentsSubjects |
| en_US_label | studentsSubjects |
| type        | manyToMany       |
| object1name | Student          |
| object2name | Subject          |

Given a relationship between two object definitions created
| name        | subjectBooks |
| en_US_label | subjectBooks |
| type        | oneToMany    |
| object1name | Subject      |
| object2name | Book         |

Given a relationship between two object definitions created
| name        | booksAuthors |
| en_US_label | booksAuthors |
| type        | manyToMany   |
| object1name | Book         |
| object2name | Author       |

Given a relationship between two object definitions created
| name        | authorAgents |
| en_US_label | authorAgents |
| type        | oneToMany    |
| object1name | Author       |
| object2name | Agent        |

Given a relationship between two object definitions created
| name        | agentsCities |
| en_US_label | agentsCities |
| type        | manyToMany   |
| object1name | Agent        |
| object2name | City         |

Given a relationship between two object definitions created
| name        | cityStates |
| en_US_label | cityStates |
| type        | oneToMany  |
| object1name | City       |
| object2name | State      |