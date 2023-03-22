package EventHub;

import java.util.HashMap;

public final class EventHubHelper {
    public static final String REQUEST_EVENT_TYPE = "REQUEST_EVENT_TYPE";
    public static HashMap<String,Integer> hm = new HashMap<>();
    static {
        hm.put("Hare",101);
    }
    /*public EventHubHelper() {
        System.out.println("Constructor EventHubHelper");
        //throw new AssertionError("Should not instantiate for a utility class");
    }*/
    private EventHubHelper() {
        throw new AssertionError("Should not instantiate for a utility class");
    }

    public static void functionA(){
        System.out.println("Function A Parent");
    }
}
