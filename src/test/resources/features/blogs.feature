Feature: Create blog post

  Scenario: Check the body
    When I make a valid call to create a blog post
      | articleBody      | This isd dmdddcy artcsicle boddy |
      | dateCreated      | 2019-04-10T23:02:50Z            |
      | dateModified     | 2019-04-11T14:39:05Z            |
      | datePublished    | 2019-04-11T14:39:00Z            |
      | encodingFormat   | text/html                       |
      | friendlyUrlPath  | myfriddedcddsndlcypath2          |
      | headline         | this didddsd my hecscadline       |
      | numberOfComments | 0                               |
    Then the blog post is being created with a valid data

  Scenario: Check the code
    When I make a valid call to create a blog post
      | articleBody      | This isd dmdddcy artsedicle boddy |
      | dateCreated      | 2019-04-10T23:02:50Z            |
      | dateModified     | 2019-04-11T14:39:05Z            |
      | datePublished    | 2019-04-11T14:39:00Z            |
      | encodingFormat   | text/html                       |
      | friendlyUrlPath  | myfriddedcefddsndlypath2         |
      | headline         | this didddsd my hesecaddline       |
      | numberOfComments | 0                               |
    Then the response code is 200

  Scenario: Create another blog post with the same data
    Given I make a valid call to create a blog post
      | articleBody      | This isd dmdddddcy artsicle boddy |
      | dateCreated      | 2019-04-10T23:02:50Z              |
      | dateModified     | 2019-04-11T14:39:05Z              |
      | datePublished    | 2019-04-11T14:39:00Z              |
      | encodingFormat   | text/html                         |
      | friendlyUrlPath  | myFrfhfh2                          |
      | headline         | this didddsd my hescadline        |
      | numberOfComments | 0                                 |
    When I make a valid call to create a blog post
      | articleBody      | This isd dmdddddcy artsicle boddy |
      | dateCreated      | 2019-04-10T23:02:50Z              |
      | dateModified     | 2019-04-11T14:39:05Z              |
      | datePublished    | 2019-04-11T14:39:00Z              |
      | encodingFormat   | text/html                         |
      | friendlyUrlPath  | myFrfhfh2                          |
      | headline         | this didddsd my hescadline        |
      | numberOfComments | 0                                 |
    Then the response code is 409