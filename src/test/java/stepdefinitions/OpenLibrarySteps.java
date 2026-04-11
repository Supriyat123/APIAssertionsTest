package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OpenLibrarySteps {

    private Response response;

//-------------------------------------------------------------------
// Extract the response
//--------------------------------------------------------------------
    @Given("I send a GET request to the Open Library author endpoint")
    public void i_send_a_get_request() {
        response = RestAssured
                .given()
                .baseUri("https://openlibrary.org")
                .header("Accept", "application/json")
                .when()
                .get("/authors/OL1A.json")
                .then()
                .extract().response();

        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());

    }

//--------------------------------------------------------------------
// Verify the API response status code matches the expected value
//--------------------------------------------------------------------
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatus) {
        assertEquals("Status code mismatch", expectedStatus, response.getStatusCode());
        System.out.println("Step passed: status code is " + expectedStatus);
    }

//------------------------------------------------------------------------------
// Verify the personal name in the API response matches the expected author name
//------------------------------------------------------------------------------

    @And("the personal_name should be {string}")
    public void the_personal_name_should_be(String expectedName) {
        String actualName = response.jsonPath().getString("personal_name");
        System.out.println("personal_name in response: " + actualName);
        assertEquals("No match found for the requested personal name", expectedName, actualName);
        System.out.println("Step passed: Found a match for the requested author name " + actualName);
    }

//-------------------------------------------------------------------------------
// Verify the alternate names list in the API response contains the expected name
//-------------------------------------------------------------------------------

    @And("the alternate_names should contain {string}")
    public void the_alternate_names_should_contain(String expectedName) {
        List<String> alternateNames =
                response.jsonPath().getList("alternate_names");
        System.out.println("alternate_names in response: " + alternateNames);
        assertTrue(
                "No match found for the requested alternate name : " + expectedName,
                alternateNames.contains(expectedName)
        );
        System.out.println("Step passed: Found a match for the alternate name " + expectedName);
    }
}