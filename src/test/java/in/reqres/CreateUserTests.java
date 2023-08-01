package in.reqres;

import models.createUser.CreateUserBodyModel;
import models.createUser.CreateUserResponse200Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.CreateUserSpecs.createUserRequestSpec;
import static specs.CreateUserSpecs.createUserResponse200Spec;

@DisplayName("Получение списка пользователей")
public class CreateUserTests {

    @DisplayName("Успешное создание пользователя")
    @Test
    void successCreateUserTest() {
        CreateUserResponse200Model createUserResponse200Model = step("Send request", () ->
                given()
                        .spec(createUserRequestSpec)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createUserResponse200Spec)
                        .extract().as(CreateUserResponse200Model.class)
        );
//        step("Verify results", () ->
//        assertEquals(2,getUserResponse200Model.getPage()));
    }

    @DisplayName("Ошибка 404 при получении пользователя из базы")
//    @Test
//    void fatalGetListUsers204Test() {
//        step("Check not found response", () ->
//                given()
//                .spec(getUsersRequestNoDataSpec)
//                .when()
//                .delete("/users?page=2")
//                .then()
//                .spec(getUsersResponse204Spec));
//    }
}
