package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
public class ORDSGetRequestWithJsonPathTest extends ORDSTestBase {

    @DisplayName("GET ords/hr/employees/103 and jsonPath")
    @Test
    public void jsonPathSingleEmpInfoTest() {
        Response response = given().accept(ContentType.JSON).
                and().get("/employees/103");

        System.out.println("Status code =" + response.statusCode());
        assertEquals(200, response.statusCode());

        System.out.println("response.contentType() = " + response.contentType());
        assertEquals("application/json", response.contentType());
        
    
        JsonPath json = response.jsonPath();
       int empID = json.getInt("employee_id");
       String firstName = json.getString("first_name");
       String lastName = json.getString("last_name");
       int salary = json.getInt("salary");

        System.out.println("empID = " + empID);
        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("salary = " + salary);

        assertEquals(103,empID);
        assertEquals("Alexander", firstName);
        assertEquals("Hunold", lastName);
        assertEquals(9000,salary);
       
    }
    @DisplayName("GET ords/hr/employees/ and usingjsinPath filters")
    @Test
    public void jsonPathFilterTest(){
        Response response = given().accept(ContentType.JSON).when().get("/employees");

        System.out.println("response.statusCode() = " + response.statusCode());
       // response.prettyPrint();
        JsonPath json = response.jsonPath();
        System.out.println("===================================================");
        List<String> empList = json.getList("items.findAll{it.department_id=90}.first_name");
        System.out.println("empList = " + empList);
        System.out.println("===================================================");
        List<String> itProgrsList = json.getList("items.findAll{it.job_id=='IT_PROG'}.first_name");
        System.out.println("itProgrsList = " + itProgrsList);
        System.out.println("===================================================");
        List<Integer> empIds = json.getList("items.findAll{it.salary>=5000}.employee_id");
        System.out.println("empIds = " + empIds);
        System.out.println("===================================================");
        String firstNameMaxSalary = json.getString("items.max{it.salary}.first_name");
        System.out.println("firstNameMaxSalary = " + firstNameMaxSalary);
        System.out.println("===================================================");
        String firstNameMinSalary = json.getString("items.min{it.salary}.first_name");
        System.out.println("firstNameMinSalary = " + firstNameMinSalary);
    }
}
