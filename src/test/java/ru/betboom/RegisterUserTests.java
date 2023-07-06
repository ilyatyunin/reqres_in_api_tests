package ru.betboom;

import models.register.RegisterBodyModel;
import models.register.RegisterResponse200Model;
import models.register.RegisterResponse400Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RegisterSpecs.*;

@DisplayName("Регистрация пользователя")
public class RegisterUserTests {

    @DisplayName("Успешная регистрация пользователя")
    @Test
    void successfullRegisterTest() {
        RegisterBodyModel registerBody = new RegisterBodyModel();
        registerBody.setEmail("eve.holt@reqres.in");
        registerBody.setPassword("pistol");

        RegisterResponse200Model registerResponse200 =
                given()
                .spec(registerRequestSpec)
                .body(registerBody)
                .when()
                .post("/register")
                .then()
                .spec(registerResponse200Spec)
                .extract().as(RegisterResponse200Model.class);
        assertEquals("QpwL5tke4Pnpja7X4", registerResponse200.getToken());
        assertEquals(4, registerResponse200.getId());
    }

    @DisplayName("Ошибка 400 при регистрации пользователя")
    @Test
    void fatalRegister400Test() {
        RegisterBodyModel registerBody = new RegisterBodyModel();
        registerBody.setEmail("fatal@reqres.in");
        registerBody.setPassword("pistol");

        RegisterResponse400Model registerResponse400 =
                given()
                .spec(registerRequestSpec)
                .body(registerBody)
                .when()
                .post("/register")
                .then()
                .spec(registerResponse400Spec)
                .extract().as(RegisterResponse400Model.class);
        assertEquals("Note: Only defined users succeed registration", registerResponse400.getError());
    }
    @DisplayName("Ошибка 415 при получении списка пользователей")
    @Test
    void fatalGetListUsers415Test() {
                given()
                .spec(registerRequestNoDataSpec)
                .when()
                .post("/users?page=2")
                .then()
                .log().status()
                .statusCode(415);
    }
}