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

public class RegisterSpecs {
    public static RequestSpecification registerRequestSpec = with()
            .log().uri()
            .log().body()
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api");

    public static RequestSpecification registerRequestNoDataSpec = with()
            .baseUri("https://reqres.in")
            .basePath("/api");
    public static ResponseSpecification registerResponse200Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("token", notNullValue())
            .expectBody("id", notNullValue())
            .expectBody(matchesJsonSchemaInClasspath("schemes/register-response-scheme.json"))
            .build();

    public static ResponseSpecification registerResponse400Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .expectBody("error", notNullValue())
            .build();

    public static ResponseSpecification registerResponse415Spec = new ResponseSpecBuilder()
            .log(STATUS)
            .expectStatusCode(415)
            .build();
}