package in.reqres;

import models.register.RegisterBodyModel;
import models.register.RegisterResponse200Model;
import models.register.RegisterResponse400Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
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

        RegisterResponse200Model registerResponse200 = step("Send request", () ->
                given()
                .spec(registerRequestSpec)
                .body(registerBody)
                .when()
                .post("/register")
                .then()
                .spec(registerResponse200Spec)
                .extract().as(RegisterResponse200Model.class));
        step("Verify results", () -> {
        assertEquals("QpwL5tke4Pnpja7X4", registerResponse200.getToken());
        assertEquals(4, registerResponse200.getId());
        });
    }

    @DisplayName("Ошибка 400 при регистрации пользователя")
    @Test
    void fatalRegister400Test() {
        RegisterBodyModel registerBody = new RegisterBodyModel();
        registerBody.setEmail("fatal@reqres.in");

        RegisterResponse400Model registerResponse400 = step("Send request", () ->
                given()
                .spec(registerRequestSpec)
                .body(registerBody)
                .when()
                .post("/register")
                .then()
                .spec(registerResponse400Spec)
                .extract().as(RegisterResponse400Model.class));
        step("Verify results", () ->
        assertEquals("Missing password", registerResponse400.getError()));
    }
}