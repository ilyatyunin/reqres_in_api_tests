package ru.betboom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

@DisplayName("Получение списка пользователей на второй странице")
public class GetListUsersTests {

    @DisplayName("Успешное получение списка пользователей")
    @Test
    void successGetListUsersTest() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemes/list-users-scheme.json"))
                .body("page", is(2));
    }

    @DisplayName("Ошибка 415 при получении списка пользователей")
    @Test
    void fatalGetListUsers415Test() {
        given()
                .log().uri()
                .when()
                .post("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .statusCode(415);
    }
}
