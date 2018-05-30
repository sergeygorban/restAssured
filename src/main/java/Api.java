import org.apache.http.HttpHeaders;

import java.util.LinkedHashMap;
import java.util.Map;


public enum Api {

    TEST("https://hooks.zapier.com/hooks/catch/3320164/afqk11","https://hooks.zapier.com/hooks/catch/3320164/afqk11", "POST", "EntityRespTest");

    private String url;
    private String urlSecond;
    private String method;
    private String className;


    Api(String url, String urlSecond, String method, String className) {

        this.url = url;
        this.urlSecond = urlSecond;
        this.className = className;
        this.method = method;

    }

    public String getUrl() {
        return url;
    }

    public String getUrlSecond() {
        return urlSecond;
    }

    public String getMethod() {
        return method;
    }

    public String getClassName() {
        return className;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(HttpHeaders.AUTHORIZATION, "111111111");
        map.put(HttpHeaders.ACCEPT, "application/json");
        map.put(HttpHeaders.CONTENT_TYPE, "application/json");
        return map;
     }
}
