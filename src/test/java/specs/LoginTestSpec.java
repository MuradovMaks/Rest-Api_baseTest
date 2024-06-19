package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustom;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class LoginTestSpec {

    public static RequestSpecification loginTestReq = with()
            .filter(withCustom())
            .basePath("/register")
            .log().headers()
            .log().body()
            .contentType(JSON);
    public static ResponseSpecification loginTestRes = new ResponseSpecBuilder()

            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}

