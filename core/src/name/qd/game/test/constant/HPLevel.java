package name.qd.game.test.constant;

import java.util.HashMap;
import java.util.Map;

public class HPLevel {
    private static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 3);
        map.put(2, 5);
        map.put(3, 7);
        map.put(4, 10);
        map.put(5, 13);
        map.put(6, 16);
        map.put(7, 20);
        map.put(8, 24);
        map.put(9, 28);
        map.put(10, 35);
    }

    public int getHp(int level) {
        return map.get(level);
    }
}