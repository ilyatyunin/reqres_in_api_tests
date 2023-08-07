package in.reqres.tests;

import in.reqres.models.register.RegisterResponse200Model;
import in.reqres.models.register.RegisterResponse400Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.RegisterSpecs.*;
import static in.reqres.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Регистрация пользователя")
public class RegisterUserTests {

    @Test
    @Tag("positive")
    @DisplayName("Успешная регистрация пользователя")
    void successfullRegisterTest() {
        RegisterResponse200Model registerResponse200 = step("Отправка запроса", () ->
                given()
                .spec(registerSuccessRequestSpec)
                .when()
                .post("/register")
                .then()
                .spec(registerResponse200Spec)
                .extract().as(RegisterResponse200Model.class));
        step("Проверка результатов ответа", () -> {
        assertEquals(REG_TOKEN, registerResponse200.getToken());
        assertEquals(REG_ID, registerResponse200.getId());
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Ошибка 400 при регистрации пользователя")
    void fatalRegister400Test() {
        RegisterResponse400Model registerResponse400 = step("Отправка запроса", () ->
                given()
                .spec(registerFatalRequestSpec)
                .when()
                .post("/register")
                .then()
                .spec(registerResponse400Spec)
                .extract().as(RegisterResponse400Model.class));
        step("Проверка результатов ответа", () ->
        assertEquals(REG_ERROR_MISSING_PASSWORD, registerResponse400.getError()));
    }
}