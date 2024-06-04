package api.helpers.delete.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertTrue;

public class DeleteUserChecks {

    @Step("Check success delete user")
    public void checkSuccessDeleteUser(Response resp){

        boolean delete= resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED)
                .extract()
                .path("success");

        assertTrue(delete);

    }
}
