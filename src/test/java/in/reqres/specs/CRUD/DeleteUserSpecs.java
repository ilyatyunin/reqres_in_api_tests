package in.reqres.specs.CRUD;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class DeleteUserSpecs {
    public static RequestSpecification deleteUserRequestSpec = with()
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api")
            .filter(withCustomTemplates());

    public static ResponseSpecification deleteUserResponse204Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(204)
            .build();
}