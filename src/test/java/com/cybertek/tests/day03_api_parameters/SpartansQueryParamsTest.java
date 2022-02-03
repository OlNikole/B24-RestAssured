package com.cybertek.tests.day03_api_parameters;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartansQueryParamsTest {

    @BeforeAll
    public static void setUp(){
        System.out.println("Set up method");
        baseURI = ConfigurationReader.getProperty("spartan.url");
}
    @Test
    public void searchForSpartanQueryTest() {
        Response response = given().accept(ContentType.JSON)
                        .and().queryParam("gender","Female")
                              .queryParam("nameContains", "e")
                              .when().get("/api/spartans/search");
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200,response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        assertEquals("application/json",response.contentType());

        System.out.println(response.asString());
        assertTrue(response.asString().contains("Female"));
        assertTrue(response.asString().contains("Janette"));


    }

}
