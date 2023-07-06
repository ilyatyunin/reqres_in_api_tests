package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class GetUsersSpecs {

    public static RequestSpecification getUsersRequestNoDataSpec = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .contentType(JSON);
    public static ResponseSpecification getUsersResponse200Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("page", notNullValue())
            .expectBody(matchesJsonSchemaInClasspath("schemes/list-users-scheme.json"))
            .build();
}