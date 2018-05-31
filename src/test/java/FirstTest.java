import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
public class FirstTest {

    @Test
    public void firstTest() throws IOException {

        EntityForRequests entityTest =
                new EntityTest("User", "Name", "Age");

        String respons = new Requests()
                .sendPost(
                        Api.TEST.getHeaders(),
                        Api.TEST.getUrl(),
                        entityTest.createFirstEntity())
                .then()
                .assertThat().statusCode(200)
                .extract().body().asString();

        assertThat(respons)
                .contains("status\": \"success")
                .contains("\"id\"");

        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

/*
        for (int i = 0; i <= 3; i++) {
            String a = new Requests().sendPost(Api.TEST.getHeaders(), Api.TEST.getUrl(), entityTest.createSecondEntity())
                    .then()
                    .assertThat().statusCode(200)
                    .extract().body().asString();

            if (a.contains("success")) break;
            Requests.expect(5000);
            throw new RuntimeException("Нет Ответа");
        }
*/


   /*
        System.out.println(new Requests().sendPost(Api.TEST.getHeaders(),Api.TEST.getUrl(),entityTest.createSecondEntity()).then().extract().body().asString() + "!!!!!!!!!!");

        Stream.generate(() -> new Requests().sendPost(Api.TEST.getHeaders(),Api.TEST.getUrl(),entityTest.createSecondEntity()))
                //.takeWhile(re -> re.then().extract().body().asString().contains("success"))
                .limit(3)
                .peek(res -> Requests.expect(10000))
                .filter(res -> res.body().asString().contains("success"))
                .forEach(e -> System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"));
*/











    }
}
