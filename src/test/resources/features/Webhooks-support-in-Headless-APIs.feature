Feature: Webhooks support in Objects API
  #https://issues.liferay.com/browse/LPS-98594

  Background:
    Given active object definitions created
      | name    | en_US_label | en_US_plural_label | requiredStringField |
      | Manager | Manager     | Managers           | firstname           |

  Scenario: On After Add webhook for Custom Object
    Given a webhook "onAfterAdd" with url "localhost:8888" for "Manager" created
    When manager accounts created after setting up the webhook
      | firstname |
      | Carinek   |
    Then the information of the "Manager" is sent to the URL defined in the webhook

# Given a site with User object defined
# And a webhook "On After Update" for User
# And a new user is created
# When updating the user
# Then the payload is matching the JSON format defined in the Headless API

# Given a site with Custom Object defined
# And a webhook "On After Delete" for Custom Object
# And a new custom object is created
# When deleting the object
# Then  the information of the object is sent to the URL defined in the webhook

# Given a site with Custom Object defined
# And a webhook "On After Delete" for Custom Object
# And a custom objects is created
# And a webhook "On After Delete" for Custom Object is deleted
# When deleting one the object
# Then the information of the object is NOT sent to the URL defined in the deleted webhook

# Given ** a site with Custom Object defined
# And a webhook "On After Delete" for Custom Object
# And a custom objects is created
# And a webhook "On After Delete" for Custom Object is updated
# When deleting one the object
# Then the information of the object is sent to the URL defined in the updated webhook