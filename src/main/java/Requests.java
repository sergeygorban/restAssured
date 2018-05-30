import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class Requests {

    public Response sendPost(Map<String, String> headers, String url, String body) {
        return RestAssured.given()
                .headers(headers)
                .baseUri(url)
                .body(body)
                .log().all()
                .post();

    }


    public static void main(String[] args) {

        new Requests()
                .sendPost(
                        Api.TEST.getHeaders(),
                        Api.TEST.getUrl(),
                        new EntityTest("User", "Name", "Age").createFirstEntity()
                );


    }
}
