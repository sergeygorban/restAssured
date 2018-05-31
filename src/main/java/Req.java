import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Req {

    private static int connectTimeout = 3000;
    private static int socketTimeout = 8000;

    public String sendRequestNew(Map<String, String> headers, String url, String body) {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        RequestBuilder request =
                RequestBuilder.create("POST")
                        .setConfig(requestConfig)
                        .setUri(url)
                        .setEntity(new StringEntity(body, "UTF-8"));

        headers.forEach(request::setHeader);
        request.build();

        try (CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(new SSLContextBuilder().
                loadTrustMaterial(null, (certificate, authType) -> true)
                .build()).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
             CloseableHttpResponse response = httpClient.execute(request.build())) {

            String res = "RESPONSE:" + "\n" + response.getStatusLine() + "\n"
                    + (response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : "") + "\n" + "";

            System.out.println(res);

            return res;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {

        EntityForRequests entityTest =
                new EntityTest("User", "Name", "Age");

        //sendRequestNew(Api.TEST.getHeaders(),Api.TEST.getUrl(), entityTest.createFirstEntity());

        Stream.generate(() -> new Req().sendRequestNew(Api.TEST.getHeaders(),Api.TEST.getUrl(), entityTest.createFirstEntity()))
                .forEach(res -> System.out.println());
    }


}
