package ru.betboom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

@DisplayName("Регистрация пользователя")
public class RegisterUserTests {

    @DisplayName("Успешная регистрация пользователя")
    @Test
    void successfullRegisterTest() {
        String registerBody = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(registerBody)
                .when()
                .post("https://reqres.in/api/register") // get -- выполнение запроса
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemes/register-response-scheme.json"))
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @DisplayName("Ошибка 400 при регистрации пользователя")
    @Test
    void fatalRegister400Test() {
        String registerBody = "{\"email\": \"fatal@reqres.in\", \"password\": \"pistol\"}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(registerBody)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Note: Only defined users succeed registration"));
    }

    @DisplayName("Ошибка 415 при регистрации пользователя")
    @Test
    void fatalRegister415Test() {
        given()
                .when()
                .post("https://reqres.in/api/register") // get -- выполнение запроса
                .then()
                .log().status()
                .statusCode(415);
    }
}
