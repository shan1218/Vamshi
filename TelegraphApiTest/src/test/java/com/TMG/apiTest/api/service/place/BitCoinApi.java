package com.TMG.apiTest.api.service.place;

import com.TMG.apiTest.restAssuredUtil.RestService;
import com.TMG.apiTest.vo.bitcoin.CurrencyConveryResponse;
import com.TMG.apiTest.vo.bitcoin.MapRefference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.Assert;

import java.util.logging.Logger;

public class BitCoinApi {

    private static final Logger LOGGER = Logger.getLogger(BitCoinApi.class.getName());

    public BitCoinApi(String endPoint) {

        RestAssured.baseURI = endPoint;
    }

    public void assertValue(io.restassured.response.Response response, String field, String value) {
        String successCode = response.jsonPath().get(field);
        junit.framework.Assert.assertEquals( "Correct Success code was returned", value, successCode);
    }

    public void createHotelWithPlace(String apiPath, String body) {
        Response response = RestService.postMethod("json", apiPath, body);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public MapRefference currencyMap(String apiPath, String place) {
        Response response = RestService.getMethod("json", apiPath + place);
        int statusCode = response.getStatusCode();
        LOGGER.info("Search Response : " + response.asString());
        //Assert.assertEquals(statusCode, 200);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response.asString(), new TypeReference<MapRefference>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CurrencyConveryResponse getCurrencyConversionResponse(String apiPath, String place) {
        Response response = RestService.getMethod("json", apiPath + place);
        int statusCode = response.getStatusCode();
        LOGGER.info("Search Response : " + response.asString());
        //Assert.assertEquals(statusCode, 200);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response.asString(), new TypeReference<CurrencyConveryResponse>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response cryptoCurrency(String apiPath, String place) {
        Response response = RestService.getMethod("json", apiPath + place);
        int statusCode = response.getStatusCode();
        LOGGER.info("Search Response : " + response.asString());
        //Assert.assertEquals(statusCode, 200);
        return response;
    }
}
