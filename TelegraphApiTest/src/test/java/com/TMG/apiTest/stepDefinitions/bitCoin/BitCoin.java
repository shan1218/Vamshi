package com.TMG.apiTest.stepDefinitions.bitCoin;

import com.TMG.apiTest.api.service.place.BitCoinApi;
import com.TMG.apiTest.api.service.place.TravelHotelApi;
import com.TMG.apiTest.api.service.place.TravelPlaceApi;
import com.TMG.apiTest.helper.PropertyReader;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.TmgUtil;
import com.TMG.apiTest.vo.bitcoin.CurrencyConveryResponse;
import com.TMG.apiTest.vo.bitcoin.Datum;
import com.TMG.apiTest.vo.bitcoin.MapRefference;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class BitCoin  extends StepDefinition {

    PropertyReader propertyFileReader = null;
    BitCoinApi bitCoinApi;
    String mapPath;
    String currencyConveryApi;

    Map<String, Integer> priceMap = new HashMap<String, Integer>();

    @Given("^I call currency map api \"([^\"]*)\"$")
    public void iCallCurrencyMapApi(String fileName) {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "bitCoin");
        mapPath = propertyFileReader.readProperty("map");
        bitCoinApi = new BitCoinApi(getEnvironment().getEndpoints() + mapPath);
        MapRefference mapRefference = bitCoinApi.currencyMap(getEnvironment().getEndpoints(), mapPath);
        for(com.TMG.apiTest.vo.bitcoin.Datum datum : mapRefference.getData()){
            if(StringUtils.equals(datum.getSymbol(),"BTC") || StringUtils.equals(datum.getSymbol(),"USDT") || StringUtils.equals(datum.getSymbol(),"ETH")){
                priceMap.put(datum.getSymbol(), datum.getId());
            }
        }
        System.out.println(priceMap);
    }

    @Then("^i convert price for given symbol \"([^\"]*)\" and \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iConvertPriceForGivenSymbolAndTo(String Symbol, String unit, String currency) throws Throwable {
        currencyConveryApi = propertyFileReader.readProperty("conversion");
        for(String source : priceMap.keySet()){
            CurrencyConveryResponse currencyConveryResponse = bitCoinApi.getCurrencyConversionResponse(getEnvironment().getEndpoints(), currencyConveryApi+"?id="+priceMap.get(source)+"&amount="+unit+"&convert="+currency);
            System.out.println(currencyConveryResponse.getData().getQuote().getBOB().getPrice());
        }
    }
}
