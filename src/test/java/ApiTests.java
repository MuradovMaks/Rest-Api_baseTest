import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApiTests {

    @BeforeAll
    public static void setUp()
    {
        RestAssured.baseURI = "https://reqres.in/";
    }

    @Test
    public void createNewUserTest() {
        String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        given()
                .body(data)
                .contentType(JSON)
                .log().all()
                .when()
                .post("api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", Is.is("morpheus"))
                .body("job", Is.is("leader"))
                .body("id", Matchers.is(Matchers.notNullValue()));

    }

    @Test
    public void loginTest() {
        String data = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        given()
                .body(data)
                .contentType(JSON)
                .log().all()
                .when()
                .post("api/register")
                .then()
                .statusCode(200)
                .body("token", Matchers.is(Matchers.notNullValue()));
    }

    @Test
    public void getListUsers() {
        given()
                .log().all()
                .when()
                .get("api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", Matchers.is(12))
                .log().all();
    }

    @Test
    public void putUser() {
        String data = "{\n" +
        "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        given()
                .body(data)
                .contentType(JSON)
                .log().all()
                .when()
                .put("api/users/2")
                .then()
                .statusCode(200)
                .body("job", Matchers.is("zion resident"))
                .log().all();
    }

    @Test
    public void deleteUser() {
        given()
                .log().all()
                .when()
                .delete("api/users/2")
                .then()
                .statusCode(204)
                .log().all();
    }
}
