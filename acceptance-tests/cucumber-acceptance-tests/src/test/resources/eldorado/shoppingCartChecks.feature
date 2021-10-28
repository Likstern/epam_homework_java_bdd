#noinspection NonAsciiCharacters
Feature: As a user i should be change cost in the shopping cart

  Background:
    Given I open home page
    And I change region of site to "Рязань"
    And I perform search by request "71382546"
    And Click add to shopping cart button
    Then Open shopping cart

  @severity=blocker @tmsLink=Test-Case-11 @UserStoryId=Test-11 @issue=eldorado-11
  Scenario: Cost in header menu is equal cost in shopping cart page
    Then Check the cost in header menu is equal to cost in shopping cart

  @severity=blocker @tmsLink=Test-Case-12 @UserStoryId=Test-12 @issue=eldorado-12
  Scenario: Cost increase if the quantity of goods is increase
    And Increase the number of similar products per unit
    Then Cost increase by the quantity of goods

  @severity=blocker @tmsLink=Test-Case-13 @UserStoryId=Test-13 @issue=eldorado-13
  Scenario: The cost decreases if the quantity of goods decreases
    When Increase the number of similar products per unit
    Then Cost increase by the quantity of goods
    When Decrease the number of similar products per unit
    Then Cost is equal a cost one product

  @severity=blocker @tmsLink=Test-Case-14 @UserStoryId=Test-14 @issue=eldorado-14
  Scenario: Cost is decrease if remove an item from shopping Cart
    And Click delete item link in shopping cart
    Then Cost is equal a zero

  @severity=blocker @tmsLink=Test-Case-15 @UserStoryId=Test-15 @issue=eldorado-15
  Scenario: Cost is restore if item restore in shopping cart
    And Click delete item link in shopping cart
    Then Cost is equal a zero
    And Click restore item link
    Then Cost is restored

  @severity=blocker @tmsLink=Test-Case-16 @UserStoryId=Test-16 @issue=eldorado-16
  Scenario: Cost is increase if user selected express service
    When User select 2 year service
    Then Cost increase by express service price
    When User select 3 year service
    Then Cost increase by express service price
    When User select 5 year service
    Then Cost increase by express service price

  @severity=blocker @tmsLink=Test-Case-17 @UserStoryId=Test-17 @issue=eldorado-17
  Scenario: The cost of shopping cart is remains if you leave the site
    When Open Yandex search service
    And Open shopping cart
    Then Cost is restored

  @severity=blocker @tmsLink=Test-Case-18 @UserStoryId=Test-18 @issue=eldorado-18
  Scenario: Cost is increase if user add service product in cart
    When User add "Игровая приставка Nintendo Classic Mini: SNES" to cart, cost is equal a sum of products in cart

  @severity=blocker @tmsLink=Test-Case-19 @UserStoryId=Test-19 @issue=eldorado-19
  Scenario: Cost is equal if a service product when product is delete
    When User add "Игровая приставка Nintendo Classic Mini: SNES" to cart
    And Click delete item link in shopping cart
    Then Cost is equal a service product

  @severity=blocker @tmsLink=Test-Case-20 @UserStoryId=Test-20 @issue=eldorado-20
  Scenario: The cost is increased by the cost of delivery
    When User select local delivery
    Then Cost is increased by the cost of delivery