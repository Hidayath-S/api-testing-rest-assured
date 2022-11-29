package com.api.testing.accountsApiTests;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class accountsAPITest {
    private static final String URI = "http://localhost:8114";

    @BeforeAll
    public static void setUp(){
        baseURI = URI;

    }

    /**
     * getAccountByAcctNum API requires a request param accountNum
     */
    @Test
    public void getAccountByAcctNumTest(){
        Response response = given().get("Account?accountNum=12356887888");
        assertEquals(200,response.getStatusCode());

        JsonPath jsonPath = response.jsonPath();

        // asserting values from the response using jsonPath
        assertEquals(Long.valueOf("12356887888"),jsonPath.get("accountNumber"));
        assertEquals("custId1",jsonPath.get("custId"));
        assertEquals("Active",jsonPath.get("acctSts"));

        //asserting using hamcrest matchers
        assertThat(jsonPath.get("balance"), Matchers.notNullValue());

        //validating json schema
        given().when().get("Account?accountNum=12356887888")
                .then().assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/jsonSchema/getAccountByAcctNumSchema.json")));
    }

}
