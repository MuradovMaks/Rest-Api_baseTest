package tests;

import io.restassured.RestAssured;
import modules.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustom;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.CreateNewUserSpec.createNewUserReq;
import static specs.CreateNewUserSpec.createNewUserRes;
import static specs.DeleteUserSpec.deleteUserReq;
import static specs.DeleteUserSpec.deleteUserRes;
import static specs.LoginTestSpec.loginTestReq;
import static specs.LoginTestSpec.loginTestRes;
import static specs.PutUserSpec.putUserReq;
import static specs.PutUserSpec.putUserRes;
import static specs.GetListUsersSpec.getUserListReq;
import static specs.GetListUsersSpec.getUserListRes;

@Tag("Api")
public class ApiTests {

    @BeforeAll
    public static void setUp()
    {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void createNewUserTest() {
        final NewUserModel newUser = new NewUserModel();
        newUser.setName("morpheus");
        newUser.setJob("leader");
        NewUserModel response = step("Make request for Create a New User", () -> {
            return  given()
                    .spec(createNewUserReq)
                    .body(newUser)

                    .when()
                    .post()
                    .then()
                    .spec(createNewUserRes)
                    .extract().as(NewUserModel.class);
        });

        step("Check Result", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
            Assertions.assertNotEquals(null, response.getId());
            Assertions.assertNotEquals(null, response.getCreatedAt());
        });
    }


    @Test
    public void loginTest() {
        final LoginModel login = new LoginModel();
        login.setEmail("eve.holt@reqres.in");
        login.setPassword("pistol");


        LoginModel login1 = step("Make Request for Authorization", () -> {
            return given()
                    .spec(loginTestReq)
                    .body(login)
                    .when()
                    .post()
                    .then()
                    .spec(loginTestRes)
                    .extract().as(LoginModel.class);
        });
        step("Check Results", () -> {
            Assertions.assertNotEquals(null, login1.getToken());
            Assertions.assertNotEquals(null, login1.getId());
        });
    }

    @Test
    public void getListUsers() {
        final ListUsersModel listUsersModel = new ListUsersModel();
        ListUsersModel list1 = step("Make Request for get List Users", () -> {
            return given()
                    .spec(getUserListReq)
                    .when()
                    .get()
                    .then()
                    .spec(getUserListRes)
                    .extract().as(ListUsersModel.class);
        });
        step("Check Result ", () -> {
            assertEquals(12, list1.getTotal());
        });

    }

    @Test
    public void putUser() {
        final UpdateUserModel updateUserModel = new UpdateUserModel();
        updateUserModel.setName("morpheus");
        updateUserModel.setJob("zion resident");
        UpdateUserModel updateUserModel1 = step("Make Request fo Update Users data", () -> {
            return given()
                    .spec(putUserReq)
                    .body(updateUserModel)
                    .when()
                    .put()
                    .then()
                    .spec(putUserRes)
                    .extract().as(UpdateUserModel.class);
        });
        step("Check Results", () -> {
            assertEquals("morpheus", updateUserModel1.getName());
            assertEquals("zion resident", updateUserModel1.getJob());
        });
    }

    @Test
    public void deleteUser() {
        final DeleteUserModel userModel = new DeleteUserModel();
        step("Make Request fo Delete", () -> {
            return given()
                    .filter(withCustom())
                    .spec(deleteUserReq)
                    .when()
                    .delete()
                    .then()
                    .spec(deleteUserRes);
        });
        step("Check Results", () -> {
            ;
            assertEquals(null, userModel.getResponse());
        });
    }
}
