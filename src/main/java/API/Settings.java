package API;

import io.restassured.RestAssured;

public class Settings {
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }
}
