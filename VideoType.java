import java.util.HashMap;
import java.util.Map;

public enum VideoType {
    VHS(1),
    CD(2),
    DVD(3),
    ;


    private static final Map<Integer, VideoType> byId = new HashMap<>();

    private final int id;

    static {
        for (VideoType e : values()) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getId());
            }
        }
    }

    public static VideoType get(int id) {
        return byId.get(id);
    }

    VideoType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
