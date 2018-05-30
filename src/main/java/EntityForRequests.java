import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class EntityForRequests {

    private String param;
    private String referens;
    private String param_1 = "TestApi";
    private String param_2 = "Test #1";
    private String param_3 = "Day";

    public EntityForRequests(String param) {
        this.param = param;
        this.referens = RandomStringUtils.randomAlphanumeric(10);
    }

    public String createFirstEntity() {
        try {
            Map<String, EntityForRequests> map = new LinkedHashMap<>();
            map.put(this.param, this);
            return new ObjectMapper().writeValueAsString(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getReferens() {
        return referens;
    }

    public String getParam_1() {
        return param_1;
    }

    public String getParam_2() {
        return param_2;
    }

    public String getParam_3() {
        return param_3;
    }
}
