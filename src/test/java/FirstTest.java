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




        //System.out.println(new Requests().sendPost(Api.TEST.getHeaders(),Api.TEST.getUrl(),entityTest.createSecondEntity()).then().extract().body().asString() + "!!!!!!!!!!");

        Stream<Response> asd = Stream.generate(() -> new Requests().sendPost(Api.TEST.getHeaders(),Api.TEST.getUrl(),entityTest.createSecondEntity()))
                //.takeWhile(re -> !re.equals("3"))
                .takeWhile(re -> !re.body().asString().contains("1success"))
                .peek(res -> Requests.expect(5000))
                
                .limit(3);
        System.out.println(asd.findFirst().get().then().extract().body().asString());



                //.
                //
                //.filter(res -> res.body().asString().contains("success"))
                //.forEach(re -> System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!"));












    }
}
