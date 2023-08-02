package in.reqres;

import models.getUsers.GetUsersResponse200Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.GetUsersSpecs.*;

@DisplayName("Получение списка пользователей")
public class GetListUsersTests {

    @DisplayName("Успешное получение списка пользователей на второй странице")
    @Test
    void successGetListUsersTest() {
        GetUsersResponse200Model getUserResponse200Model = step("Send request", () ->
                given()
                        .spec(getUsersRequestWithJsonSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(getUsersResponse200Spec)
                        .extract().as(GetUsersResponse200Model.class)
        );
        step("Verify results", () ->
        assertEquals(2,getUserResponse200Model.getPage()));
    }

    @DisplayName("Ошибка 415 при получении списка пользователей")
    @Test
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

