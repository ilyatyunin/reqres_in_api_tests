package in.reqres.specs.crud;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static in.reqres.tests.TestData.getCreateUserTestData;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserSpecs {
    public static RequestSpecification createUserRequestSpec = with()
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api")
            .body(getCreateUserTestData())
            .filter(withCustomTemplates());

    public static ResponseSpecification createUserResponse200Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(201)
            .expectBody("id", notNullValue())
            .expectBody("createdAt", notNullValue())
            .expectBody(matchesJsonSchemaInClasspath("schemes/create-user-response-scheme.json"))
            .build();
}