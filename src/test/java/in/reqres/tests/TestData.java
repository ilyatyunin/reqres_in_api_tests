package in.reqres.tests;

import in.reqres.models.crud.createuser.CreateUserBodyModel;
import in.reqres.models.crud.updateuser.UpdateUserBodyModel;
import in.reqres.models.register.RegisterBodyModel;

public class TestData {
//    Данные для запросов
    public static CreateUserBodyModel getCreateUserTestData() {
        CreateUserBodyModel createUserBodyModel = new CreateUserBodyModel();
        createUserBodyModel.setName("Ilya");
        createUserBodyModel.setJob("QA");
        return createUserBodyModel;
    }

    public static UpdateUserBodyModel getUpdateUserTestData() {
        UpdateUserBodyModel updateUserBody = new UpdateUserBodyModel();
        updateUserBody.setName("Andrey");
        updateUserBody.setJob("developer");
        return updateUserBody;
    }

    public static RegisterBodyModel getSuccessRegisterTestData() {
        RegisterBodyModel registerBody = new RegisterBodyModel();
        registerBody.setEmail("eve.holt@reqres.in");
        registerBody.setPassword("pistol");
        return registerBody;
    }

    public static RegisterBodyModel getFatalRegisterTestData() {
        RegisterBodyModel registerBody = new RegisterBodyModel();
        registerBody.setEmail("fatal@reqres.in");
        return registerBody;
    }

//    Данные для проверок
    public static final String REG_TOKEN = "QpwL5tke4Pnpja7X4";
    public static final int REG_ID = 4;
    public static final String REG_ERROR_MISSING_PASSWORD = "Missing password";

    public static final int READ_USER_ID = 2;
    public static final String READ_USER_EMAIL = "janet.weaver@reqres.in";
    public static final String READ_USER_FIRST_NAME = "Janet";
    public static final String READ_USER_LAST_NAME = "Weaver";
    public static final String READ_USER_AVATAR = "https://reqres.in/img/faces/2-image.jpg";

    public static final int READ_USERS_PAGE = 2;
    public static final int READ_USERS_PER_PAGE = 6;
    public static final int READ_USERS_TOTAL = 12;
    public static final int READ_USERS_TOTAL_PAGES = 2;
}
