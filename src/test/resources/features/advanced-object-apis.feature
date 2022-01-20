Feature: Extend Object APIs with advanced features
  # https://issues.liferay.com/browse/LPS-134171

  Scenario: Nested fields in OneToOne relationship
    Given two objects of two different types created
    Given a relationship of type OneToOne between two objects created
    When calling for object1 with object2 as nestedfields parameter
    Then object1 information should be provided with an object2 information nested as an object