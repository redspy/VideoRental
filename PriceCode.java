import java.util.HashMap;
import java.util.Map;

public enum PriceCode {
    REGULAR(1),
    NEW_RELEASE(2),
    ;


    private static final Map<Integer, PriceCode> byId = new HashMap<>();

    private final int id;

    static {
        for (PriceCode e : values()) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getId());
            }
        }
    }

    public static PriceCode get(int id) {
        return byId.get(id);
    }

    PriceCode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
