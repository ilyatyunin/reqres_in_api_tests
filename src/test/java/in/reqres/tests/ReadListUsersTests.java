package in.reqres.tests;

import in.reqres.models.readUsers.ReadUsersResponse200Model;
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

    @DisplayName("Успешное получение списка пользователей на второй странице")
    @Test
    @Tag("positive")
    void successGetListUsersTest() {
        ReadUsersResponse200Model getUserResponse200Model = step("Send request", () ->
                given()
                        .spec(getUsersRequestWithJsonSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(getUsersResponse200Spec)
                        .extract().as(ReadUsersResponse200Model.class)
        );
        step("Verify results", () -> {
            assertEquals(READ_USERS_PAGE, getUserResponse200Model.getPage());
            assertEquals(READ_USERS_PER_PAGE, getUserResponse200Model.getPerPage());
            assertEquals(READ_USERS_TOTAL, getUserResponse200Model.getTotal());
            assertEquals(READ_USERS_TOTAL_PAGES, getUserResponse200Model.getTotalPages());
        });

    }

    @DisplayName("Ошибка 415 при получении списка пользователей")
    @Test
    @Tag("negative")
    void fatalGetListUsers415Test() {
        step("Check not found response", () ->
                given()
                        .spec(getUsersRequestNoDataSpec)
                        .when()
                        .post("/users?page=2")
                        .then()
                        .spec(getUserResponse415Spec));
    }
}

