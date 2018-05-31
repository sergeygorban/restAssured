import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Stream;

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

    public static void expect(long millis) {

        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }



    public <T extends EntityRespTest> T sendRequest(Api api) throws ClassNotFoundException {

        return sendPost(
                api.getHeaders(),
                api.getUrl(),
                new EntityTest("User", "Name", "Age").createFirstEntity()
        ).body().as((Type) Class.forName(api.getClassName()));
    }

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(System.currentTimeMillis());

        int a = LocalTime.now().toSecondOfDay();

        System.out.println(Duration.between(LocalTime.now(), LocalDateTime.now()).getSeconds());
        System.out.println(LocalTime.now().toSecondOfDay());

        EntityForRequests entityTest =
                new EntityTest("User", "Name", "Age");

       Stream.generate(() -> new Requests().sendPost(Api.TEST.getHeaders(),Api.TEST.getUrl(),entityTest.createSecondEntity()).then().extract().body().asString())
               .forEach(e -> System.out.println(e));
                //.takeWhile(res -> res.body().asString().contains("success1"));
                //.forEach(res -> System.out.println("!!!!!!!!!!!!!!!!"));
                //.peek(res -> Requests.expect(25000));

        Stream.generate(() -> "1111").forEach(e -> System.out.println(e));


    }

}
