package name.qd.game.test.constant;

public enum Level {
    PENCIL(1, "Pencil plaza"),
    BBB(2, "Basket"),
    CCC(3, "CC"),
    DDD(4, "Dog"),
    EEE(5, "Elastic"),
    FFF(6, "FFFFF"),
    GGG(7, "GG"),
    HHH(8, "H"),
    III(9, "I"),
    FINAL(10, "The final xxx");

    private int level;
    private String displayName;

    Level(int level, String displayName) {
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
