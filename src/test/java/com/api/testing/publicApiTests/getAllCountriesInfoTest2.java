package com.api.testing.publicApiTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test uses a public API
 */

public class getAllCountriesInfoTest2 {

    private static final String URI = "https://restcountries.com/v3.1";

    @BeforeAll
    public static void setUp(){
        baseURI = URI;
        useRelaxedHTTPSValidation();
    }

    @Test
    public void getAllCountryDetails(){

        Response getCountryRsp =  given().get("/all").thenReturn();
        //System.out.println("response : "+getCountryRsp.getBody().prettyPrint());
        assertEquals(200,getCountryRsp.getStatusCode());

        // retrieving values using jsonPath
        //JsonPath jsonPath = getCountryRsp.jsonPath();
        //assertEquals("موريتانيا",jsonPath.get("name.nativeName.ara.common"));

    }

    @Test
    public void getCountryDetailsByName(){

        Response getCountryRsp =  given().get("/name/india").thenReturn();
        //System.out.println("response : "+getCountryRsp.getBody().prettyPrint());
        assertEquals(200,getCountryRsp.getStatusCode());

        // retrieving values using jsonPath
        //JsonPath jsonPath = getCountryRsp.jsonPath();
        //assertEquals("موريتانيا",jsonPath.get("name.nativeName.ara.common"));
    }
}