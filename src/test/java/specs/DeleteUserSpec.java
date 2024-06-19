package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustom;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class DeleteUserSpec {
    public static RequestSpecification deleteUserReq = with()
            .filter(withCustom())
            .basePath("/users/2")
            .log().headers()
            .log().body()
            .contentType(JSON);
    public static ResponseSpecification deleteUserRes = new ResponseSpecBuilder()

            .expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .build();
}
