Feature: Crypto Info

  @api
  Scenario Outline: Find Crypto Info
    Given I find currency info for the symbol "<bitCoin>" and "<Symbol>"
    Then i confirm Logo url "<LogoUrl>"
    Then i confirm technical doc "<technicalDoc>"
    Then i confirm currency Symbol "<currencySymbol>"
    Then i confirm the date added "<dateAdded>"
    Then i confirm the platform
    Then i confirm mineable "<mineable>"

    Examples:
      | bitCoin | Symbol| LogoUrl|technicalDoc|currencySymbol|dateAdded|mineable|
      | apiUrl |   1027    |     https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png|https://github.com/ethereum/wiki/wiki/White-Paper|ETH|2015-08-07T00:00:00.000Z|mineable|

