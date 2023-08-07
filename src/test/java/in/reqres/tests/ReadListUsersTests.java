package in.reqres.tests;

import in.reqres.models.readuser.ReadUsersResponse200Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static in.reqres.specs.ReadUsersSpecs.*;

@DisplayName("Получение списка пользователей")
public class ReadListUsersTests {

    @Test
    @Tag("positive")
    @DisplayName("Успешное получение списка пользователей на второй странице")
    void successGetListUsersTest() {
        ReadUsersResponse200Model getUserResponse200Model = step("Отправка запроса", () ->
                given()
                        .spec(getUsersRequestWithJsonSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(getUsersResponse200Spec)
                        .extract().as(ReadUsersResponse200Model.class)
        );
        step("Проверка результатов ответа", () -> {
            assertEquals(READ_USERS_PAGE, getUserResponse200Model.getPage());
            assertEquals(READ_USERS_PER_PAGE, getUserResponse200Model.getPerPage());
            assertEquals(READ_USERS_TOTAL, getUserResponse200Model.getTotal());
            assertEquals(READ_USERS_TOTAL_PAGES, getUserResponse200Model.getTotalPages());
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Ошибка 415 при получении списка пользователей")
    void fatalGetListUsers415Test() {
        step("Отправка запроса", () ->
                given()
                        .spec(getUsersRequestNoDataSpec)
                        .when()
                        .post("/users?page=2")
                        .then()
                        .spec(getUserResponse415Spec));
    }
}

