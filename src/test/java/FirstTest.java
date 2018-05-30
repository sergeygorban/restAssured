import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
public class FirstTest {

    @Test
    public void firstTest() throws IOException {

        EntityForRequests entityTest =
                new EntityTest("User", "Name", "Age");

        Response firstResponse = new Requests()
                .sendPost(
                        Api.TEST.getHeaders(),
                        Api.TEST.getUrl(),
                        entityTest.createFirstEntity()
                );

        assertThat(firstResponse.statusCode()).isEqualTo(200);
        assertThat(firstResponse.as(EntityResponses.class).getStatus()).isEqualTo("success");

        Response secondResponse = new Requests()
                .sendPost(
                        Api.TEST.getHeaders(),
                        Api.TEST.getUrl(),
                        entityTest.createSecondEntity()
                );

        entityTest.createFirstEntity();










    }
}
