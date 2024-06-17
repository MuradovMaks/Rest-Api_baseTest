package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustom;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class getListUsersSpec {
    public static RequestSpecification getUserListReq = with()
            .filter(withCustom())
            .basePath("api/users?page=2")
            .log().headers()
            .log().body()
            .contentType(JSON);
    public static ResponseSpecification getUserListRes = new ResponseSpecBuilder()

            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
