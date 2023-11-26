package testCases;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;


public class RestApiTest {

    private static final String BASE_URL = "https://restful-api.dev";
    private static String objectId = null;

    @Test(priority = 1)
    @Description("Validate the GET request")
    public void testGetRequest() {
        step("Perform GET request");
        Response response = RestAssured.get(BASE_URL);

        step("Assert the status code");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Unexpected status code");

        step("Assert the response body or perform additional validations if needed");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("title"), "Response body doesn't contain expected content");
    }


    @Test(priority = 2)
    @Description("Validate the POST request")
    public void testPostRequest() {

        step("Preparing POST request payload");
        String requestBody = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";

        step("Perform POST request");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://api.restful-api.dev/objects");

        step("Assert the status code");
        System.out.println(response.getBody());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Unexpected status code");
        step("Assert the response body or perform additional validations if needed");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Apple MacBook Pro 16"), "Response body doesn't contain expected content");
        Assert.assertTrue(responseBody.contains("Intel Core i9"), "Response body doesn't contain expected content");
        objectId = response.jsonPath().getString("id");
        System.out.println("Created ID: " + objectId);

    }


    @Test(priority = 3)
    @Description("Validate the PUT request")
    public void testPutRequest() {
        step("Preparing PUT request payload");
        String requestBody = "{\n" +
                "   \"name\": \"Updated MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2020,\n" +
                "      \"price\": 2049.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\",\n" +
                "      \"color\": \"silver\"\n" +
                "   }\n" +
                "}";

        step("Perform PUT request");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("https://api.restful-api.dev/objects/" + objectId);

        step("Assert the status code");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Unexpected status code");
        step("Assert the response body or perform additional validations if needed");
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Updated MacBook Pro 16"), "Response body doesn't contain expected content");
        Assert.assertTrue(responseBody.contains("Intel Core i9"), "Response body doesn't contain expected content");
        Assert.assertTrue(responseBody.contains("silver"), "Response body doesn't contain expected content");
        String id = response.jsonPath().getString("id");
        System.out.println("Updated ID: " + id);
    }


    @Test(priority = 4)
    @Description("Validate the DELETE request")
    @Story("Validate DELETE Request")
    public void testDeleteRequest() {
        step("Perform DELETE request");
        Response response = RestAssured
                .when()
                .delete("https://api.restful-api.dev/objects/" + objectId);

        step("Assert the status code");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Unexpected status code");
        step("Assert the response body or perform additional validations if needed");
        String responseBody = response.getBody().asString();
        Assert.assertEquals(response.jsonPath().getString("message"), "Object with id = " + objectId + " has been deleted.", "Incorrect deletion message");
    }


}












