package api.helpers.create.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.net.HttpURLConnection;
import static org.junit.Assert.*;

public class CreateUserChecks {

    @Step("Check success create user")
    public void checkSuccessCreateUser(Response resp){

        boolean create= resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("success");

        assertTrue(create);

    }

    @Step("Check already create user")
    public void checkAlreadyCreateUser(Response resp) {

        boolean create = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .extract()
                .path("success");

        assertFalse(create);
    }

    @Step("Check required body create user")
    public void checkRequiredBody(Response resp) {

        String create = resp
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .extract()
                .path("message");

        assertEquals("Email, password and name are required fields",create);

    }
}
