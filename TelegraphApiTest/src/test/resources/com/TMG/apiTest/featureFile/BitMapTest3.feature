Feature: Bit Coin Test 3

  @api
  Scenario Outline: Bit Coin Test 3
    Given I retrice first ten currencies "<bitCoin>" and "<maxId>"
    Then I check mimable tag association

    Examples:
      | bitCoin | maxId|
      | apiUrl |   10    |

