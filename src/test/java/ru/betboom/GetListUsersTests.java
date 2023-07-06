package ru.betboom;

import models.getUsers.GetUsersResponse200Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.GetUsersSpecs.getUsersRequestNoDataSpec;
import static specs.GetUsersSpecs.getUsersResponse200Spec;

@DisplayName("Получение списка пользователей на второй странице")
public class GetListUsersTests {

    @DisplayName("Успешное получение списка пользователей")
    @Test
    void successGetListUsersTest() {
        GetUsersResponse200Model getUserResponse200Model =
                given()
                .spec(getUsersRequestNoDataSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(getUsersResponse200Spec)
                .extract().as(GetUsersResponse200Model.class);
        assertEquals(2,getUserResponse200Model.getPage());
    }

    @DisplayName("Ошибка 204 при получении списка пользователей")
    @Test
    void fatalGetListUsers204Test() {
        given()
                .spec(getUsersRequestNoDataSpec)
                .when()
                .delete("/users?page=2")
                .then()
                .log().status()
                .statusCode(204);
    }
}
