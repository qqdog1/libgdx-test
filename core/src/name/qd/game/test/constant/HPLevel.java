package name.qd.game.test.constant;

import java.util.HashMap;
import java.util.Map;

public class HPLevel {
    private static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(0, 3);
        map.put(1, 4);
        map.put(2, 5);
        map.put(3, 6);
        map.put(4, 7);
        map.put(5, 8);
        map.put(6, 9);
        map.put(7, 10);
        map.put(8, 11);
        map.put(9, 12);
        map.put(10, 13);
    }

    public static int getHp(int level) {
        return map.get(level);
    }
}