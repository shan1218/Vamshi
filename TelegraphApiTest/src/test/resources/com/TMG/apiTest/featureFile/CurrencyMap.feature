Feature: Create Search Travel Place

  @api
  Scenario Outline: Create Places and Hotels
    Given I call currency map api "<bitCoin>"
    Then i convert price for given symbol "<Symbol>" and "<Unit>" to "<Currency>"

    Examples:
      | bitCoin | Symbol| Unit|Currency|
      | apiUrl |   BTC,ETH,USDT    |10|BOB  |

