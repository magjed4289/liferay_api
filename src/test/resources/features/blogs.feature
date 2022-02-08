Feature: Create blog post

  Scenario: Check the body
    #noinspection SpellCheckingInspection
    When I make a valid call to create a blog post
      | articleBody      | This isd dmdddcy artcdsicle boddy |
      | dateCreated      | 2019-04-10T23:02:50Z              |
      | dateModified     | 2019-04-11T14:39:05Z              |
      | datePublished    | 2019-04-11T14:39:00Z              |
      | encodingFormat   | text/html                         |
      | friendlyUrlPath  | myfriendlypath                    |
      | headline         | this is my headline               |
      | numberOfComments | 0                                 |
    Then the blog post is being created with a valid data

  Scenario: Check the code
    When I make a valid call to create a blog post
      | articleBody      | This is an article's body |
      | dateCreated      | 2019-04-10T23:02:50Z      |
      | dateModified     | 2019-04-11T14:39:05Z      |
      | datePublished    | 2019-04-11T14:39:00Z      |
      | encodingFormat   | text/html                 |
      | friendlyUrlPath  | myfriendlypath            |
      | headline         | this is my headline       |
      | numberOfComments | 0                         |
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