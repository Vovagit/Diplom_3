package api.helpers;

import constants.EnvConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class Client {

    public RequestSpecification spec() {
        return given().log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri(EnvConfig.BASE_URL);
    }
}
