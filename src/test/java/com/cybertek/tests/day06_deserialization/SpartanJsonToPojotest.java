package com.cybertek.tests.day06_deserialization;

import com.cybertek.tests.SpartanTestBase;
import com.cybertek.tests.pojo.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanJsonToPojotest extends SpartanTestBase {

    Response response = given().accept(ContentType.JSON)
            .when().get("/api/spartans/24");

    Spartan spartan = response.as(Spartan.class);



    //add some word

    


}
