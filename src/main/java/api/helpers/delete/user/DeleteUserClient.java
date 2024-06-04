package api.helpers.delete.user;

import api.helpers.Client;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DeleteUserClient extends Client {

    public static final String DELETE_USER_PATH= "/api/auth/user";

    @Step("Delete User Request")
    public Response deleteUserRequest(String token){
        return spec()
                .auth()
                .oauth2(token.replace("Bearer ",""))
                .delete(DELETE_USER_PATH);
    }
}
