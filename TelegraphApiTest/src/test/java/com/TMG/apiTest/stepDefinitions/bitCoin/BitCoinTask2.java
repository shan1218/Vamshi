package com.TMG.apiTest.stepDefinitions.bitCoin;

import com.TMG.apiTest.api.service.place.BitCoinApi;
import com.TMG.apiTest.helper.PropertyReader;
import com.TMG.apiTest.helper.StepDefinition;
import com.TMG.apiTest.helper.TmgUtil;
import com.TMG.apiTest.vo.bitcoin.MapRefference;
import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;

public class BitCoinTask2  extends StepDefinition {

    PropertyReader propertyFileReader = null;
    BitCoinApi bitCoinApi;
    String cryptoCurrencyApi;
    Response response;

    @Given("^I find currency info for the symbol \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iFindCurrencyInfoForTheSymbolAnd(String fileName, String symbol) throws Throwable {
        propertyFileReader = TmgUtil.loadPropertyFile(fileName, "bitCoin");
        cryptoCurrencyApi = propertyFileReader.readProperty("cryptoCurrency");
        bitCoinApi = new BitCoinApi(getEnvironment().getEndpoints() + cryptoCurrencyApi);
        response = bitCoinApi.cryptoCurrency(getEnvironment().getEndpoints(), cryptoCurrencyApi+symbol);
        System.out.println(response.jsonPath().getString("data.1027.id"));
    }

    @Then("^i confirm Logo url \"([^\"]*)\"$")
    public void iConfirmLogoUrl(String logoUrl) throws Throwable {
        //System.out.println(logoUrl);
        //System.out.println(response.jsonPath().getString("data.1027.logo"));
        System.out.println(StringUtils.equals(response.jsonPath().getString("data.1027.logo"), logoUrl));
    }

    @Then("^i confirm technical doc \"([^\"]*)\"$")
    public void iConfirmTechnicalDoc(String technicalDoc) throws Throwable {
        System.out.println(StringUtils.contains(response.jsonPath().getString("data.1027.urls.technical_doc"), technicalDoc));
    }

    @Then("^i confirm currency Symbol \"([^\"]*)\"$")
    public void iConfirmCurrencySymbol(String currencySymbol) throws Throwable {
        System.out.println(StringUtils.contains(response.jsonPath().getString("data.1027.symbol"), currencySymbol));
    }

    @Then("^i confirm the date added \"([^\"]*)\"$")
    public void iConfirmTheDateAdded(String dateAdded) throws Throwable {
        System.out.println(StringUtils.contains(response.jsonPath().getString("data.1027.date_added"), dateAdded));
    }

    @Then("^i confirm the platform$")
    public void iConfirmThePlatform() {
        System.out.println(StringUtils.equals(response.jsonPath().getString("data.1027.platform"), null));
    }

    @Then("^i confirm mineable \"([^\"]*)\"$")
    public void iConfirmMineable(String minable) throws Throwable {
        System.out.println(StringUtils.contains(response.jsonPath().getString("data.1027.tags"), minable));
    }
}
