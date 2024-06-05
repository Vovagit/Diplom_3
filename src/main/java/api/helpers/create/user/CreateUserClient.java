package api.helpers.create.user;

import api.helpers.Client;
import api.pojo.CreateUser;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CreateUserClient extends Client {
    private static final String CREATE_USER_PATH= "/api/auth/register";

    @Step("Create user request")
    public Response createUserRequest(CreateUser createBody){
        return spec()
                .body(createBody)
                .when()
                .post(CREATE_USER_PATH);
    }

}
