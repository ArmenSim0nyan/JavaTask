package API;

import API.Builders.PostBuilder;
import API.Builders.UserBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;

public class APITask {
    Settings settings = new Settings();
    UserBuilder userBuilder = new UserBuilder();
    int userId = userBuilder.randomUserId(10);

    @BeforeClass
    public void before() {
        settings.setup();
    }

    @Test
    public void task() {
        // Get user with random id and print email
        Response userRes = get("/users/" + userId)
                .then().statusCode(200)
                .extract().response();
        JsonPath user = userRes.jsonPath();
        System.out.println(user.getString("email"));

        // Get user posts and check their ids
        Response userPosts = given().param("userId", userId)
                .when().get("/posts")
                .then().statusCode(200)
                .extract().response();

        List<Integer> ids = userPosts.jsonPath().getList("id");

        ids.forEach(id -> {
           assertThat(id, is(greaterThanOrEqualTo(0)));
           assertThat(id, is(lessThanOrEqualTo(100)));
        });

        // Add a post with same user id and check response
        PostBuilder postBuilder = new PostBuilder(userId);
        given()
                .contentType("application/json; charset=UTF-8").body(postBuilder)
                .when().post("/posts")
                .then().statusCode(201)
                .and().body("id", equalTo(101))
                .and().body("title", equalTo(postBuilder.getTitle()))
                .and().body("body", equalTo(postBuilder.getBody()))
                .and().body("userId", equalTo(postBuilder.getUserId()))
                .extract().response();
    }
}
