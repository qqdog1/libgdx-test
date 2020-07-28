package name.qd.game.test.constant;

import java.util.HashMap;
import java.util.Map;

public class HPLevel {
    private static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(0, 3);
        map.put(1, 5);
        map.put(2, 7);
        map.put(3, 10);
        map.put(4, 13);
        map.put(5, 16);
        map.put(6, 20);
        map.put(7, 24);
        map.put(8, 28);
        map.put(9, 33);
        map.put(10, 39);
    }

    public int getHp(int level) {
        return map.get(level);
    }
}