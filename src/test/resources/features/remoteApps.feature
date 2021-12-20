Feature: Create remote appp as an iFrame

  Scenario: Check the code
    When I make a valid call to create a remote app as iframe
      | friendlyURLMapping  |                                  |
      | iFrameURL           | https://www.liferday.com/mydappd |
      | instanceable        | true                             |
      | nameMap             | my remote app as ifrdamed        |
      | portletCategoryName | RemoteApps                       |
      | properties          |                                  |
      | description         |                                  |
      | sourceCodeURL       |                                  |
    Then the response code is 200

  Scenario: Check the body response
    When I make a valid call to create a remote app as iframe
      | friendlyURLMapping  |                                           |
      | iFrameURL           | https://www.lifedderay.cdom/mydedfancyapp |
      | instanceable        | true                                      |
      | nameMap             | my app as an eidfddramed                  |
      | portletCategoryName | RemoteApps                                |
      | properties          |                                           |
      | description         |                                           |
      | sourceCodeURL       |                                           |
    Then I receive a proper body response in return