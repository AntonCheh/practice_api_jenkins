package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TakeDataUsers {

    private Response response;

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String url) {
        response = RestAssured.get(url);
    }

    @Then("I receive a successful response with status code {int}")
    public void iReceiveASuccessfulResponseWithStatusCode(int statusCode) {
        Assert.assertEquals("status code not 200",statusCode, response.getStatusCode());

    }

    @And("the list of users is not empty")
    public void theListOfUsersIsNotEmpty() {
        List<?> users = response.jsonPath().getList("data");
        Assert.assertFalse("User list should not be empty", users.isEmpty());

    }

    @And("all avatar filenames of the users are unique")
    public void allAvatarFilenamesOfTheUsersAreUnique() {
        List<String> avatarUrls = response.jsonPath().getList("data.avatar");
        Set<String> uniqueAvatars = new HashSet<>(avatarUrls);
        Assert.assertEquals("Avatar filenames should be unique", uniqueAvatars.size(), avatarUrls.size());
    }

    @And("the response contains page number {int} and total users {int}")
    public void theResponseContainsPageNumberAndTotalUsers(int expectedPage, int expectedTotal) {
        int actualPage = response.jsonPath().getInt("page");
        int actualTotal = response.jsonPath().getInt("total");
        Assert.assertEquals("Expected page number does not match", expectedPage, actualPage);
        Assert.assertEquals("Expected total users does not match", expectedTotal, actualTotal);
    }

    @And("the number of users per page is {int}")
    public void theNumberOfUsersPerPageIs(int expectedPerPage) {
        int actualPerPage = response.jsonPath().getInt("per_page");
        Assert.assertEquals("Expected number of users per page does not match", expectedPerPage, actualPerPage);
    }
}
