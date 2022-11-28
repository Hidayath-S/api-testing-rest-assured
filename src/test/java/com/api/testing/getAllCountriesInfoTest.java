package com.api.testing;



import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class getAllCountriesInfoTest {

    private static final String URI = "https://api.first.org/data/v1";

    @BeforeAll
    public static void setUp(){
        baseURI = URI;
        useRelaxedHTTPSValidation();
    }

    @Test
    public void getCountryDetails(){

        Response getCountryRsp =  given().get("/countries").thenReturn();
        //System.out.println("response : "+getCountryRsp.getBody().prettyPrint());
        assertEquals(200,getCountryRsp.getStatusCode());
        assertEquals("OK",getCountryRsp.getStatusLine());

    }

    @Test
    public void getNewsDetails(){

        Response getCountryRsp =  given().get("/news").thenReturn();
        //System.out.println("response : "+getCountryRsp.getBody().prettyPrint());
        assertEquals(200,getCountryRsp.getStatusCode());

    }

    @Test
    public void getTeamDetails(){

        Response getCountryRsp =  given().get("/teams").thenReturn();
        //System.out.println("response : "+getCountryRsp.getBody().prettyPrint());
        assertEquals(200,getCountryRsp.getStatusCode());

    }
}
