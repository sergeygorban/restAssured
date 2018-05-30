public class EntityTest extends EntityForRequests {

    private String param_4;
    private String param_5;
    private String param_6;

    public EntityTest(String param_4, String param_5, String param_6) {
        super("a.testRequest");
        this.param_4 = param_4;
        this.param_5 = param_5;
        this.param_6 = param_6;

    }
}
