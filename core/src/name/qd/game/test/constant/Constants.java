package name.qd.game.test.constant;

public class Constants {
    // 每1/60秒(graphic time, render time) = 你系統過多久(physical time)
    // 假設現在移動速度是每秒1 pixel, SYSTEM_TIMESTAMP是1/30
    // 所以每1/60秒會畫下一幀
    // 但在這一幀 物體移動距離會是 1 * 1/30 = 1/30
    // 更新率和系統時間是分開的
    public static final float SYSTEM_TIMESTAMP = 1/60f;
    public static final int SYSTEM_VELOCITY_ITERATIONS = 8;
    public static final int SYSTEM_POSITION_ITERATIONS = 3;

    public static final float PIXEL_PER_METER = 20f;
    public static final int TOTAL_STAGES = 10;

    public static final int MAX_UPGRADE_LEVEL = 10;
}
