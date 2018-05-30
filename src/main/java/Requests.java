import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.util.Map;

public class Requests {

    public Response sendPost(Map<String, String> headers, String url, String body) {
        return RestAssured.given()
                .headers(headers)
                .baseUri(url)
                .body(body)
                .log().all()
                .post()
                .then().log().all()
                .extract().response();
    }

    public <T extends EntityRespTest> T sendRequest(Api api) throws ClassNotFoundException {

        return sendPost(
                api.getHeaders(),
                api.getUrl(),
                new EntityTest("User", "Name", "Age").createFirstEntity()
        ).body().as((Type) Class.forName(api.getClassName()));
    }

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(new Requests().sendRequest(Api.TEST).getAttempt());
    }
}
