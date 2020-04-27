package com.TMG.apiTest.stepDefinitions.bitCoin;

import com.TMG.apiTest.api.service.place.BitCoinApi;
import com.TMG.apiTest.helper.PropertyReader;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.TmgUtil;
import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class BitCointTask3 extends StepDefinition {

    PropertyReader propertyFileReader = null;
    BitCoinApi bitCoinApi;
    String cryptoCurrencyApi;
    Response response;
    Map<Integer, Response> resposneMap = new TreeMap<Integer, Response>();
    List<String> mimeable = new ArrayList<String>();

    @Given("^I retrice first ten currencies \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iRetriceFirstTenCurrenciesAnd(String fileName, String maxId) throws Throwable {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "bitCoin");
        cryptoCurrencyApi = propertyFileReader.readProperty("cryptoCurrency");
        bitCoinApi = new BitCoinApi(getEnvironment().getEndpoints() + cryptoCurrencyApi);
        for(int i = 1;i<=Integer.parseInt(maxId);i++) {
            response = bitCoinApi.cryptoCurrency(getEnvironment().getEndpoints(), cryptoCurrencyApi + i);
            resposneMap.put(Integer.parseInt(response.jsonPath().getString("data."+i+".id")), response);
        }
        System.out.println(resposneMap);
    }

    @Then("^I check mimable tag association$")
    public void iCheckMimableTagAssociation() {
        for(Integer id : resposneMap.keySet()){
            Response response = resposneMap.get(id);
            System.out.println(StringUtils.contains(response.jsonPath().getString("data."+id+".tags"), "mineable"));
            if(StringUtils.contains(response.jsonPath().getString("data."+id+".tags"), "mineable")){
                mimeable.add(response.jsonPath().getString("data."+id+".symbol"));
            }
        }
        System.out.println(mimeable);
    }
}
