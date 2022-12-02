package com.api.testing.edgeCases;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static io.restassured.RestAssured.given;

import com.github.tomakehurst.wiremock.http.HttpHeaders;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class mockAPITest {

   public  static WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8116));

    static String requestJson = "{ \"custId\":\"custId1\", \"fromAcctNo\":\"1235688755\", \"toAcctNo\":\"1235688757\", " +
            "\"amount\":\"1\", \"remarks\":\"Test transfer\" }";


    @BeforeAll
    public static void setUp(){
        // expected response json

        String responseJson = "{ \"PaymentResponse\": { \"amount\": 1.0, \"paymentId\": 1, \"toAcctNo\": " +
                "\"1235688757\",\"paymentCreatedDate\": \"2022-12-02\",\n" +
                "        \"customerDetails\": { \"firstName\": \"Albert\", \"lastName\": \"Einstein\",\"ecn\": " +
                "5558275231, \"phoneNumber\": 8874562320,         \"custId\": \"custId1\", \"ssn\": 7842556\n" +
                "        },\n" +
                "        \"fromAcctNo\": \"1235688755\",\"paymentStatus\": \"SUCCESS\",\"remarks\": \"Test transfer\" }}";

        // setup the stub for payment api
        wireMockServer.stubFor(post("/api/payment").withHeader("Content-Type",containing("application/json"))
                .withRequestBody(equalTo(requestJson)).willReturn(jsonResponse(responseJson,200).withHeader("Content-Type","application/json")));
        wireMockServer.start();

    }

    @Test
    public void mockApiTest(){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.headers("Content-Type","application/json");
        requestSpecification.body(requestJson);
        Response response = requestSpecification.post("http://localhost:8116/api/payment");
        JsonPath jsonPath = response.jsonPath();
        assertEquals("SUCCESS",jsonPath.get("PaymentResponse.paymentStatus"));
    }

    @AfterAll
    public static void tearDown(){
        wireMockServer.stop();
    }
}
