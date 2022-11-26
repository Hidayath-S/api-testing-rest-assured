package com.api.testing;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class getAllCountriesInfoTest {

    private static final String BASEURI = "https://api.first.org/data/v1";

    @BeforeAll
    public static void setUp(){
        baseURI = BASEURI;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void getCountryDetails(){

        Response getCountryRsp = (Response) get("/countries").getBody();
        System.out.println("response : "+getCountryRsp.getBody().prettyPrint());
        getCountryRsp.then().statusCode(200);

    }

    @Test
    public void getNewsDetails(){

        Response getCountryRsp = (Response) get("/news").getBody();
        System.out.println("response : "+getCountryRsp.getBody().prettyPrint());
        getCountryRsp.then().statusCode(200);

    }

    @Test
    public void getTeamDetails(){

        Response getCountryRsp = (Response) get("/teams").getBody();
        System.out.println("response : "+getCountryRsp.getBody().prettyPrint());
        getCountryRsp.then().statusCode(200);

    }
}
