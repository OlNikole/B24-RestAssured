package com.cybertek.tests.day04_ords_path;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetSpartanUsingPath extends SpartanTestBase {
    /**
     * Given accept is json
     * And path param id is 13
     * When I send get request to /api/spartans/{id}
     * Then status code is 200
     * And content type is json
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */

    @Test
    public void ReadJsonWithPathTest(){

      Response response = given().accept(ContentType.JSON).
              and().pathParam("id", 13).when().get("api/spartans/{id}");
      
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("contentType() = " + response.contentType());
        assertEquals("application/json", response.contentType());

        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone number = " + response.path("phone"));

        int  id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phoneNum = response.path("phone");
        System.out.println("========================================");
        System.out.println("PRINTING VARIABLES");
        System.out.println("========================================");
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phoneNum = " + phoneNum);

        assertEquals(13,id);
        assertEquals("Jaimie", name);
        assertEquals("Female",gender);
        assertEquals(7842554879L,phoneNum);
        /**
         *  assertEquals(13,response.path("id"));
         *         assertEquals("Jaimie", response.path("name"));
         *         assertEquals("Female",response.path("gender")); same way
         *          assertEquals("7842554879",response.path("phone").toString());
         */

        response.prettyPrint();
    }

    /**
     Given accept is json
     When I send get request to /api/spartans
     Then status code is 200
     And content type is json
     And I can navigate json array object
     */

    @Test
    public void readJsonArrayTest() {
        Response response = given().accept(ContentType.JSON).
                when().get("api/spartans");
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("contentType() = " + response.contentType());
        assertEquals("application/json", response.contentType());

        System.out.println("id= "+response.path("id"));
        System.out.println("========================================");
        System.out.println("ID of first spartan " + response.path("[0].id"));
        System.out.println("========================================");
        System.out.println("Name of second person= " + response.path("[1].name"));

        System.out.println("========================================");
        System.out.println("ID of first spartan " + response.path("[3].id"));
        System.out.println("Name of second person= " + response.path("[3].name"));
        System.out.println("Name of second person= " + response.path("[3].gender"));
        System.out.println("Name of second person= " + response.path("[3].phone"));

        /**
         *         System.out.println( response.path("[3].id").toString());
         *         System.out.println( response.path("[3].name").toString());
         *         System.out.println( response.path("[3].gender").toString());
         *         System.out.println( response.path("[3].phone").toString());
         */
        System.out.println(response.path("[3]").toString());
        System.out.println("========================================");
                 System.out.println( response.path("id[-1]").toString());
                 System.out.println( response.path("name[-1]").toString());
                 System.out.println( response.path("gender[-1]").toString());
                 System.out.println( response.path("phone[-1]").toString());

                 //Store all first names into a List

        List<String> allNames = response.path("name");
        System.out.println("Names  count= " + allNames.size());
        for (String eachName : allNames) {
            System.out.println("Each Name = " + eachName);
        }
        System.out.println(allNames.contains("Wooden Tester"));
        }
    }

