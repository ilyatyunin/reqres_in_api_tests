package in.reqres.specs.CRUD;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static in.reqres.tests.TestData.getUpdateUserTestData;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateUserSpecs {
    public static RequestSpecification updateUserRequestSpec = with()
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api")
            .body(getUpdateUserTestData())
            .filter(withCustomTemplates());

    public static ResponseSpecification updateUserResponse200Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("updatedAt", notNullValue())
            .expectBody(matchesJsonSchemaInClasspath("schemes/update-user-response-scheme.json"))
            .build();
}