#noinspection NonAsciiCharacters
Feature: Search Test
  As a user I should be able submit search request

  Background:
    Given I open home page
    And I change region of site to "Рязань"

  @severity=blocker @tmsLink=Test-Case-1 @UserStoryId=Test-1 @issue=eldorado-1
  Scenario Outline: Search Checking for unauthorized user
    When I perform search by request "<request>"
    Then URL of product search page should be valid and has "<request>" in query
    Examples:
      | request |
      | Android |
      | Iphone  |

  @severity=blocker @tmsLink=Test-Case-2 @UserStoryId=Test-2 @issue=eldorado-2
  Scenario Outline: Check tooltip after enter request in search form
    When I enter search request "<request>" into search field
    Then Search form showing tooltip with similar "<request>" products
    Examples:
      | request |
      | sony |

  @severity=blocker @tmsLink=Test-Case-3 @UserStoryId=Test-3 @issue=eldorado-3
  Scenario: Search line clear button erase search line
    When I enter search request "HTC" into search field
    And Click search line clear button
    Then search line is empty

  @severity=blocker @tmsLink=Test-Case-4 @UserStoryId=Test-4 @issue=eldorado-4
  Scenario Outline: Check tooltip after enter request in search form
    When I select category option "<option>"
    And I perform search by request "<request>"
    Then URL of product search page should be valid and has "<request>" and "<option_value>"in query
    Examples:
      | option              | option_value | request |
      | Смартфоны и гаджеты | 6000         | iphone  |

  @severity=blocker @tmsLink=Test-Case-5 @UserStoryId=Test-5 @issue=eldorado-5
  Scenario: Common Elements
    When I perform search by request "Samsung"
    And Check the number of snippets is "10"
    And Check that Button 'Добавить в корзину' is present
    And Catalog filter is present

  @severity=blocker @tmsLink=Test-Case-6 @UserStoryId=Test-6 @issue=eldorado-6
  Scenario: User can change quantity of snippets on search page
    When I perform search by request "HTC"
    Then Check the number of snippets is "10"
    And change quantity of snippets to "20"
    Then Check the number of snippets is "20"
    And change quantity of snippets to "50"
    Then Check the number of snippets is "50"

  @severity=blocker @tmsLink=Test-Case-7 @UserStoryId=Test-7 @issue=eldorado-7
  Scenario Outline: Change titles of snippets
    When I perform search by request "<request>"
    Then Check the title of snippets equal to "<request>"
    Examples:
      | request |
      | sony |

  @severity=blocker @tmsLink=Test-Case-8 @UserStoryId=Test-8 @issue=eldorado-8
  Scenario: Empty searches returns a search results page without snippets
    When I perform search by request " "
    Then Page has no snippets

  @severity=blocker @tmsLink=Test-Case-9 @UserStoryId=Test-9 @issue=eldorado-9
  Scenario: Search results can look like list or blocks list
    When I perform search by request "HTC"
    And Search results can look like "list"
    When I click "block" view icon
    Then Search results can look like "block"

  @severity=blocker @tmsLink=Test-Case-10 @UserStoryId=Test-10 @issue=eldorado-10
  Scenario Outline: Search by product ID leads to the product page
    When I perform search by request "<request>"
    Then URL of product page should be valid and has "<request>" in path
    Examples:
      | request |
      | 71134532 |
