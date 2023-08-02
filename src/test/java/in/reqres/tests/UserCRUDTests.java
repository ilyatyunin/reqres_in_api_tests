package in.reqres.tests;

import in.reqres.models.CRUDUser.createUser.CreateUserResponse201Model;
import in.reqres.models.CRUDUser.readUser.ReadUserResponse200Model;
import in.reqres.models.CRUDUser.updateUser.UpdateUserResponse200Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static in.reqres.specs.CRUD.CreateUserSpecs.createUserRequestSpec;
import static in.reqres.specs.CRUD.CreateUserSpecs.createUserResponse200Spec;
import static in.reqres.specs.CRUD.DeleteUserSpecs.deleteUserRequestSpec;
import static in.reqres.specs.CRUD.DeleteUserSpecs.deleteUserResponse204Spec;
import static in.reqres.specs.CRUD.ReadUserSpecs.*;
import static in.reqres.specs.CRUD.UpdateUserSpecs.updateUserRequestSpec;
import static in.reqres.specs.CRUD.UpdateUserSpecs.updateUserResponse200Spec;

@DisplayName("CRUD операции над пользователем")
public class UserCRUDTests {

    @DisplayName("Успешное создание пользователя")
    @Test
    @Tag("positive")
    void successUserCreateTest() {
        CreateUserResponse201Model createUserResponse201Model = step("Send request", () ->
                given()
                        .spec(createUserRequestSpec)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponse200Spec)
                        .extract().as(CreateUserResponse201Model.class)
        );
        step("Verify results", () -> {
            assertEquals(getCreateUserTestData().getName(), createUserResponse201Model.getName());
            assertEquals(getCreateUserTestData().getJob(), createUserResponse201Model.getJob());
            assertFalse(createUserResponse201Model.getId().isEmpty());
            assertFalse(createUserResponse201Model.getCreatedAt().isEmpty());
        });
    }

    @DisplayName("Успешное чтение пользователя")
    @Test
    @Tag("positive")
    void successUserReadTest() {
        ReadUserResponse200Model readUserResponse200Model = step("Send request", () ->
                given()
                        .spec(readUserRequestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(readUserResponse200Spec)
                        .extract().as(ReadUserResponse200Model.class)
        );
        step("Verify results", () -> {
            assertEquals(READ_USER_ID, readUserResponse200Model.getData().getId());
            assertEquals(READ_USER_EMAIL, readUserResponse200Model.getData().getEmail());
            assertEquals(READ_USER_FIRST_NAME, readUserResponse200Model.getData().getFirstName());
            assertEquals(READ_USER_LAST_NAME, readUserResponse200Model.getData().getLastName());
            assertEquals(READ_USER_AVATAR, readUserResponse200Model.getData().getAvatar());
        });
    }

    @DisplayName("Ошибка 404 при чтении пользователя")
    @Test
    @Tag("negative")
    void fatalUserRead404Test() {
        step("Send request", () ->
                given()
                .spec(readUserRequestSpec)
                .when()
                .get("/users/23")
                .then()
                .spec(readUserResponse404Spec)
        );
    }

    @DisplayName("Успешное обновление данных пользователя")
    @Test
    @Tag("positive")
    void successUserUpdateTest() {
        UpdateUserResponse200Model updateUserResponse200Model = step("Send request", () ->
            given()
                .spec(updateUserRequestSpec)
                .when()
                .put("/users/2")
                .then()
                .spec(updateUserResponse200Spec)
                .extract().as(UpdateUserResponse200Model.class)
);
    step("Verify results", () -> {
        assertEquals(getUpdateUserTestData().getName(), updateUserResponse200Model.getName());
        assertEquals(getUpdateUserTestData().getJob(), updateUserResponse200Model.getJob());
        assertFalse(updateUserResponse200Model.getUpdatedAt().isEmpty());
    });
    }

    @DisplayName("Успешное удаление пользователя")
    @Test
    @Tag("positive")
    void successUserDeleteTest() {
        step("Send request", () ->
                given()
                        .spec(deleteUserRequestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(deleteUserResponse204Spec));
    }
}
