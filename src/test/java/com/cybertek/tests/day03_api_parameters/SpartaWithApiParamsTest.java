package com.cybertek.tests.day03_api_parameters;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class SpartaWithApiParamsTest {
    @BeforeAll
    public static void setUp(){
        System.out.println("Set up method");
        baseURI = ConfigurationReader.getProperty("spartan.url");

    }

    @Test
    public void getSpartaPathParamTest(){
        Response response = given().accept(ContentType.JSON).
                when().get("/api/spartans/24");
        System.out.println("status code = " + response.statusCode());
     assertEquals(200,response.statusCode());
        System.out.println("content type " + response.contentType());
   assertEquals("application/json",response.contentType());
        response.prettyPrint();
      assertTrue(response.asString().contains("Julio"));

    }

    @Test
    public void getSpartanPathParamNegativeTest(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 1000)
                .when().get("/api/spartans/{id}");
        System.out.println("statusLine() = " + response.statusLine());
        assertEquals(404,response.statusCode());
        System.out.println("contentType() = " + response.contentType());
        assertEquals("application/json",response.contentType());
        response.prettyPrint();
        assertTrue(response.asString().contains("Not Found"));


    }

}
