package com.api.testing.accountsApiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class accountsAPITest {
    private static final String URI = "http://localhost:8114";

    @BeforeAll
    public static void setUp() {
        baseURI = URI;

    }

    /**
     * getAccountByAcctNum API requires a request param accountNum
     * Using @CSVSource to parameteraize the test
     */
    @ParameterizedTest
    @CsvSource(value = {"1235688755,Active,1000,custId1", "1235688756,Active,2000,custId1",
            "1235688757,Active,3000,custId2", "1235688758,Active,3000,custId2"})
    public void getAccountByAcctNumTest(Integer accountNum, String acctSts, Float balance, String custId) {
        Response response = given().request().log().all()
                .queryParam("accountNum",accountNum)
                .get("/Account");
        //System.out.println(response.prettyPrint());
        assertEquals(200, response.getStatusCode());

        JsonPath jsonPath = response.jsonPath();

        // asserting values from the response using jsonPath
        assertEquals(accountNum, jsonPath.get("accountNumber"));
        assertEquals(balance, jsonPath.get("balance"));
        assertEquals(custId, jsonPath.get("custId"));
        assertEquals(acctSts,jsonPath.get("acctSts"));

        //asserting using hamcrest matchers
        assertThat(jsonPath.get("balance"), Matchers.is(instanceOf(Float.class)));
        assertThat(jsonPath.get("accountNumber"),Matchers.is(instanceOf(Integer.class)));
        assertThat(jsonPath.get("acctSts"),Matchers.is(instanceOf(String.class)));
        assertThat(jsonPath.get("custId"),Matchers.is(instanceOf(String.class)));

    }

}
