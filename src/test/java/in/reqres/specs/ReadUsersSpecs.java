package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class ReadUsersSpecs {

    public static RequestSpecification getUsersRequestWithJsonSpec = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .contentType(JSON);

    public static RequestSpecification getUsersRequestNoDataSpec = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .filter(withCustomTemplates());

    public static ResponseSpecification getUsersResponse200Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("page", notNullValue())
            .expectBody("per_page", notNullValue())
            .expectBody("total", notNullValue())
            .expectBody("total_pages", notNullValue())
            .expectBody("data", notNullValue())
            .expectBody(matchesJsonSchemaInClasspath("schemes/list-users-response-scheme.json"))
            .build();

    public static ResponseSpecification getUserResponse415Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(415)
            .build();
}