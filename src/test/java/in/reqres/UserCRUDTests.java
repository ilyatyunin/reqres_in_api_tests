package in.reqres;

import models.CRUDUser.createUser.CreateUserBodyModel;
import models.CRUDUser.createUser.CreateUserResponse201Model;
import models.CRUDUser.readUser.ReadUserResponse200Model;
import models.CRUDUser.updateUser.UpdateUserBodyModel;
import models.CRUDUser.updateUser.UpdateUserResponse200Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static specs.CreateUserSpecs.createUserRequestSpec;
import static specs.CreateUserSpecs.createUserResponse200Spec;
import static specs.DeleteUserSpecs.deleteUserRequestSpec;
import static specs.DeleteUserSpecs.deleteUserResponse204Spec;
import static specs.ReadUserSpecs.*;
import static specs.UpdateUserSpecs.updateUserRequestSpec;
import static specs.UpdateUserSpecs.updateUserResponse200Spec;

@DisplayName("CRUD операции над пользователем")
public class UserCRUDTests {

    @DisplayName("Успешное создание пользователя")
    @Test
    void successUserCreateTest() {
        CreateUserBodyModel createUserBodyModel = new CreateUserBodyModel();
        createUserBodyModel.setName("Ilya");
        createUserBodyModel.setJob("qa");

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
            assertFalse(createUserResponse201Model.getId().isEmpty());
            assertFalse(createUserResponse201Model.getCreatedAt().isEmpty());
        });
    }

    @DisplayName("Успешное чтение пользователя")
    @Test
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
            assertEquals(2, readUserResponse200Model.getData().getId());
            assertEquals("janet.weaver@reqres.in", readUserResponse200Model.getData().getEmail());
            assertEquals("Janet", readUserResponse200Model.getData().getFirstName());
            assertEquals("Weaver", readUserResponse200Model.getData().getLastName());
            assertEquals("https://reqres.in/img/faces/2-image.jpg",
                    readUserResponse200Model.getData().getAvatar());
        });
    }

    @DisplayName("Ошибка 404 при чтении пользователя")
    @Test
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
    void successUserUpdateTest() {
        UpdateUserBodyModel updateUserBodyModel = new UpdateUserBodyModel();
        updateUserBodyModel.setName("morpheus");
        updateUserBodyModel.setJob("zion resident");

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
        assertFalse(updateUserResponse200Model.getUpdatedAt().isEmpty());
    });
    }

    @DisplayName("Успешное удаление пользователя")
    @Test
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
