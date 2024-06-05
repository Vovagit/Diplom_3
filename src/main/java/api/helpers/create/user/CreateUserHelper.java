package api.helpers.create.user;

import api.pojo.CreateUser;
import io.restassured.response.Response;

public class CreateUserHelper {

    private static final CreateUserClient userClient = new CreateUserClient();
    private static final CreateUserChecks userChecks = new CreateUserChecks();
    private String token;

    public void createUser(String email,String name,String password){

        CreateUser createBody = new CreateUser(email,password,name);
        Response clientResp = userClient.createUserRequest(createBody);
        userChecks.checkSuccessCreateUser(clientResp);
        token = clientResp.path("accessToken");
    }

    public String getToken() {
        return token;
    }
}
