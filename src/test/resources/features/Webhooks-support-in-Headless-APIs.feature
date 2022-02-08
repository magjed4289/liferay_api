Feature: Webhooks support in Objects API
  #https://issues.liferay.com/browse/LPS-98594

  Scenario: On After Add webhook for Custom Object
    Given active object definitions created
      | name    | en_US_label | en_US_plural_label | requiredStringField |
      | Manager | Manager     | Managers           | firstname           |
    Given a webhook "onAfterAdd" with URL "localhost:8888" for "Manager" created
    When manager accounts created after setting up the webhook
      | firstname |
      | Anthony   |
    Then the information is sent to the URL defined in the webhook

  Scenario: On After Update webhook for User
    Given a site with "User" object defined
    And a webhook "onAfterUpdate" with URL "localhost:7777" for "User" created
    And a new user is created
      | alternateName | myAlternateName1     |
      | emailAddress  | user1@myusertest.com |
      | givenName     | firstname            |
      | familyName    | familyName           |
    When updating the user after setting up the webhook
      | alternateName        | myAlternateName1       |
      | alternateNameUpdated | myAlternateNameUpdated |
      | emailAddress         | user1@myusertest.com   |
      | givenName            | firstname              |
      | familyName           | familyName             |
    Then  the payload is matching the JSON format defined for "userUpdate" in the Headless API

  Scenario: On After Delete webhook for Custom Object
    Given active object definitions created
      | name    | en_US_label | en_US_plural_label | requiredStringField |
      | Manager | Manager     | Managers           | firstname           |
    And a webhook "onAfterDelete" with URL "localhost:5555" for "Manager" created
    And manager accounts created
      | firstname |
      | Clarissa  |
    When manager account deleted after setting up the webhook
    Then  the payload is matching the JSON format defined for "managerDeletion" in the Headless API