package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ORDSGetEmployeesPathTest extends ORDSTestBase {

    @Test
    public void getAllITProgrammersTest() {

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("q" , "{\"job_id\":\"IT_PROG\"}" );

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramMap)
                .when().get("/employees");

        System.out.println("response.statusCode() = " + response.statusCode());
        //response.prettyPrint();

        System.out.println("First employee id = " + response.path("items[0].employee_id"));
        System.out.println("First employee first name= = " + response.path("items[0].first_name"));
        System.out.println("First employee last  name= = " + response.path("items[0].last_name"));
        System.out.println("First employee first email= = " + response.path("items[0].email"));

        List<String> allEmails = response.path("items.email");
        System.out.println("All Emails = " + allEmails);

        List<String> allPhones = response.path("items.phone_number");
        System.out.println("All Phones = " + allPhones);

       assertTrue(allPhones.contains("590.423.4567"));



    }
}
