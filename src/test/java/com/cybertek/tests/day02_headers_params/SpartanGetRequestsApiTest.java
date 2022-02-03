package com.cybertek.tests.day02_headers_params;

import com.cybertek.utilities.ConfigurationReader;
import com.sun.tools.javac.util.Assert;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartanGetRequestsApiTest {

String baseUrl = ConfigurationReader.getProperty("spartan.url");

@Test
    public void getAllSpartansTest(){

    Response response = get(baseUrl + "/api/spartans");
    System.out.println("Status code= " + response.statusCode());
    Assertions.assertEquals(200,response.statusCode());
    response.prettyPrint();
    assertTrue(response.asString().contains("Correy"));


}
@Test
    public void getAllSpartansHeaderTest(){
    Response response = given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans");

    System.out.println("response.statusCode() = " + response.statusCode());
    assertEquals(200,response.statusCode());
    System.out.println("response.contentType() = " + response.contentType());
    assertEquals("application/json",response.contentType());
    System.out.println("Date header value = " +response.getHeader("Date"));
    System.out.println("Transfer-Encoding " + response.getHeader("Transfer-Encoding"));
    assertTrue(response.getHeaders().hasHeaderWithName("Date"));



}
}
