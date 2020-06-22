package name.qd.game.test.constant;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private static List<Level> lst = new ArrayList<>();

    static {
        lst.add(new Level(1, "Pencil"));
        lst.add(new Level(2, "Eraser"));
        lst.add(new Level(3, "abc"));
        lst.add(new Level(4, "XXX"));
        lst.add(new Level(5, "5566"));
        lst.add(new Level(6, "666"));
        lst.add(new Level(7, "77777"));
        lst.add(new Level(8, "888"));
        lst.add(new Level(9, "999"));
        lst.add(new Level(10, "final"));
    }

    public static Level getLevel(int index) {
        return lst.get(index);
    }

    private int level;
    private String displayName;

    private Level(int level, String displayName) {
        this.level = level;
        this.displayName = displayName;
    }

    public int getLevel() {
        return level;
    }

    public String getDisplayName() {
        return displayName;
    }
}
