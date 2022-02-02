Feature: Create blog post

  Scenario: Check the body
    #noinspection SpellCheckingInspection
    When I make a valid call to create a blog post
      | articleBody      | This isd dmdddcy artcdsicle boddy |
      | dateCreated      | 2019-04-10T23:02:50Z              |
      | dateModified     | 2019-04-11T14:39:05Z              |
      | datePublished    | 2019-04-11T14:39:00Z              |
      | encodingFormat   | text/html                         |
      | friendlyUrlPath  | myfriddedcddsnddlcypath2          |
      | headline         | this didddsd my hecdscadline      |
      | numberOfComments | 0                                 |
    Then the blog post is being created with a valid data

  Scenario: Check the code
    #noinspection SpellCheckingInspection
    When I make a valid call to create a blog post
      | articleBody      | This isd dmddddcy artsedicle boddy |
      | dateCreated      | 2019-04-10T23:02:50Z               |
      | dateModified     | 2019-04-11T14:39:05Z               |
      | datePublished    | 2019-04-11T14:39:00Z               |
      | encodingFormat   | text/html                          |
      | friendlyUrlPath  | myfriddedcefdddsndlypath2          |
      | headline         | this diddddsd my hesecaddline      |
      | numberOfComments | 0                                  |
    Then the response code is 200

  Scenario: Create another blog post with the same data
    #noinspection SpellCheckingInspection
    Given I make a valid call to create a blog post
      | articleBody      | This isd dmdddddcy artsicle boddyddd |
      | dateCreated      | 2019-04-10T23:02:50Z                 |
      | dateModified     | 2019-04-11T14:39:05Z                 |
      | datePublished    | 2019-04-11T14:39:00Z                 |
      | encodingFormat   | text/html                            |
      | friendlyUrlPath  | myFrfhfh2ddd                         |
      | headline         | this didddsd my hescadlineddd        |
      | numberOfComments | 0                                    |
    #noinspection SpellCheckingInspection
    When I make a valid call to create a blog post
      | articleBody      | This isd dmdddddcy artsicle boddyddd |
      | dateCreated      | 2019-04-10T23:02:50Z                 |
      | dateModified     | 2019-04-11T14:39:05Z                 |
      | datePublished    | 2019-04-11T14:39:00Z                 |
      | encodingFormat   | text/html                            |
      | friendlyUrlPath  | myFrfhfh2ddd                         |
      | headline         | this didddsd my hescadlineddd        |
      | numberOfComments | 0                                    |
    Then the response code is 409