import org.apache.http.HttpHeaders;

import java.util.LinkedHashMap;
import java.util.Map;


public enum Api {

    TEST("https://hooks.zapier.com/hooks/catch/3320164/afqk11", "POST");

    private String url;
    private String method;

    Api(String url, String method) {

        this.url = url;
        this.method = method;

    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(HttpHeaders.AUTHORIZATION, "111111111");
        map.put(HttpHeaders.ACCEPT, "application/json");
        return map;
     }
}
