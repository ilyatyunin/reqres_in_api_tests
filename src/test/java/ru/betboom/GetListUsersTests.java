package ru.betboom;

import models.getUsers.GetUsersResponse200Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.GetUsersSpecs.*;

@DisplayName("Получение списка пользователей на второй странице")
public class GetListUsersTests {

    @DisplayName("Успешное получение списка пользователей")
    @Test
    void successGetListUsersTest() {
        GetUsersResponse200Model getUserResponse200Model = step("Send request", () ->
                given()
                        .spec(getUsersRequestNoDataSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(getUsersResponse200Spec)
                        .extract().as(GetUsersResponse200Model.class)
        );
        step("Verify results", () ->
        assertEquals(2,getUserResponse200Model.getPage()));
    }

    @DisplayName("Ошибка 204 при получении списка пользователей")
    @Test
    void fatalGetListUsers204Test() {
        step("Check not found response", () ->
                given()
                .spec(getUsersRequestNoDataSpec)
                .when()
                .delete("/users?page=2")
                .then()
                .spec(getUsersResponse204Spec));
    }
}
