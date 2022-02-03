package com.cybertek.tests.day06_deserialization;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanJsonToCollectionsTest extends SpartanTestBase {
@Test
    public void singleSpartanToMapTest(){

    Response response = given().accept(ContentType.JSON)
            .when().get("/api/spartans/24");
    Map<String,Object> spartanMap = response.as(Map.class);
    System.out.println("spartanMap = " + spartanMap);
    System.out.println("spartanMap.get(\"id\") = " + spartanMap.get("id"));
    assertEquals(24,spartanMap.get("id"));

    System.out.println("name = " + spartanMap.get("name"));
    assertEquals("Julio", spartanMap.get("name"));

    System.out.println("gender = " + spartanMap.get("gender"));
    assertEquals("Male", spartanMap.get("gender"));

    System.out.println("phone = " + spartanMap.get("phone"));
    assertEquals(9393139934L, spartanMap.get("phone"));



    Map<String, Object> expected = new HashMap<>();
    expected.put("id", 24);
    expected.put("name", "Julio");
    expected.put("gender", "Male");
    expected.put("phone" , 9393139934L);

    assertEquals(expected , spartanMap);
}


    @Test
    public void allSpartansToMapListTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        List<Map<String, Object>> spartansList = response.as(List.class);
        System.out.println("spartansList = " + spartansList);

        System.out.println("first spartan info = " + spartansList.get(0));

        System.out.println("second spartan info = " + spartansList.get(1));

        System.out.println("third spartan's id = " + spartansList.get(2).get("id"));
        
        Map<String,Object> firstSpartan = spartansList.get(0);
        System.out.println("first Spartan = " + firstSpartan);

        for (Map<String, Object> eachSpartan : spartansList) {
            System.out.println("eachSpartan.get(\"id\") = " + eachSpartan.get("id"));
            
        }
          spartansList.forEach(eachSp -> System.out.println("eachSp.get(\"name\") = " + eachSp.get("name"))); //lambda
        }
}




