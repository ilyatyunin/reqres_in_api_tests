package in.reqres.tests;

import in.reqres.models.crud.CreateUserResponse201Model;
import in.reqres.models.crud.ReadUserResponse200Model;
import in.reqres.models.crud.UpdateUserResponse200Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static in.reqres.specs.crud.CreateUserSpecs.createUserRequestSpec;
import static in.reqres.specs.crud.CreateUserSpecs.createUserResponse200Spec;
import static in.reqres.specs.crud.DeleteUserSpecs.deleteUserRequestSpec;
import static in.reqres.specs.crud.DeleteUserSpecs.deleteUserResponse204Spec;
import static in.reqres.specs.crud.ReadUserSpecs.*;
import static in.reqres.specs.crud.UpdateUserSpecs.updateUserRequestSpec;
import static in.reqres.specs.crud.UpdateUserSpecs.updateUserResponse200Spec;

@DisplayName("CRUD операции над пользователем")
public class UserCRUDTests {

    @Test
    @Tag("positive")
    @DisplayName("Успешное создание пользователя")
    void successUserCreateTest() {
        CreateUserResponse201Model createUserResponse201Model = step("Отправка запроса", () ->
                given()
                        .spec(createUserRequestSpec)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponse200Spec)
                        .extract().as(CreateUserResponse201Model.class)
        );
        step("Проверка результатов ответа", () -> {
            assertEquals(getCreateUserTestData().getName(), createUserResponse201Model.getName());
            assertEquals(getCreateUserTestData().getJob(), createUserResponse201Model.getJob());
            assertFalse(createUserResponse201Model.getId().isEmpty());
            assertFalse(createUserResponse201Model.getCreatedAt().isEmpty());
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Успешное чтение пользователя")
    void successUserReadTest() {
        ReadUserResponse200Model readUserResponse200Model = step("Отправка запроса", () ->
                given()
                        .spec(readUserRequestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(readUserResponse200Spec)
                        .extract().as(ReadUserResponse200Model.class)
        );
        step("Проверка результатов ответа", () -> {
            assertEquals(READ_USER_ID, readUserResponse200Model.getData().getId());
            assertEquals(READ_USER_EMAIL, readUserResponse200Model.getData().getEmail());
            assertEquals(READ_USER_FIRST_NAME, readUserResponse200Model.getData().getFirstName());
            assertEquals(READ_USER_LAST_NAME, readUserResponse200Model.getData().getLastName());
            assertEquals(READ_USER_AVATAR, readUserResponse200Model.getData().getAvatar());
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Ошибка 404 при чтении пользователя")
    void fatalUserRead404Test() {
        step("Отправка запроса", () ->
                given()
                .spec(readUserRequestSpec)
                .when()
                .get("/users/23")
                .then()
                .spec(readUserResponse404Spec)
        );
    }

    @Test
    @Tag("positive")
    @DisplayName("Успешное обновление данных пользователя")
    void successUserUpdateTest() {
        UpdateUserResponse200Model updateUserResponse200Model = step("Отправка запроса", () ->
            given()
                .spec(updateUserRequestSpec)
                .when()
                .put("/users/2")
                .then()
                .spec(updateUserResponse200Spec)
                .extract().as(UpdateUserResponse200Model.class)
        );
        step("Проверка результатов ответа", () -> {
            assertEquals(getUpdateUserTestData().getName(), updateUserResponse200Model.getName());
            assertEquals(getUpdateUserTestData().getJob(), updateUserResponse200Model.getJob());
            assertFalse(updateUserResponse200Model.getUpdatedAt().isEmpty());
        });
    }

    @Test
    @Tag("positive")
    @DisplayName("Успешное удаление пользователя")
    void successUserDeleteTest() {
        step("Отправка запроса", () ->
                given()
                        .spec(deleteUserRequestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(deleteUserResponse204Spec));
    }
}
