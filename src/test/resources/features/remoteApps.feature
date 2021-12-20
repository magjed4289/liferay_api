Feature: Create remote appp as an iFrame

  Scenario: Check the code
    When I make a valid call to create a remote app as iframe
      | friendlyURLMapping  |                                |
      | iFrameURL           | https://www.liferday.com/mydapp  |
      | instanceable        | true                           |
      | nameMap             | my remote app as ifrdame        |
      | portletCategoryName | RemoteApps                     |
      | properties          |                                |
      | description         |                                |
      | sourceCodeURL       |                                |
    Then the response code is 200

  Scenario: Check the body response
    When I make a valid call to create a remote app as iframe
      | friendlyURLMapping  |                                |
      | iFrameURL           | https://www.lifedderay.cdom/myedfancyapp  |
      | instanceable        | true                           |
      | nameMap             | my app as an eidfddrame          |
      | portletCategoryName | RemoteApps                     |
      | properties          |                                |
      | description         |                                |
      | sourceCodeURL       |                                |
    Then I receive a proper body response in return