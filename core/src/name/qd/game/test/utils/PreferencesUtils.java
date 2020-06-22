package name.qd.game.test.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesUtils {
    public enum PreferencesEnum {
        L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,
        LEVEL, // level not exist, see intro
        SOUND,
        MUSIC,
    }

    private static Preferences values = Gdx.app.getPreferences("values");

    public static void clear() {
        values.clear();
    }

    public static void remove(PreferencesEnum preferencesEnum) {
        values.remove(preferencesEnum.name());
    }

    public static void set(PreferencesEnum preferencesEnum, int value) {
        values.putInteger(preferencesEnum.name(), value);
        values.flush();
    }

    public static void set(PreferencesEnum preferencesEnum, float value) {
        values.putFloat(preferencesEnum.name(), value);
        values.flush();
    }

    public static void set(PreferencesEnum preferencesEnum, String value) {
        values.putString(preferencesEnum.name(), value);
        values.flush();
    }

    public static void set(PreferencesEnum preferencesEnum, boolean value) {
        values.putBoolean(preferencesEnum.name(), value);
        values.flush();
    }

    public static void set(PreferencesEnum preferencesEnum, long value) {
        values.putLong(preferencesEnum.name(), value);
        values.flush();
    }

    public static int getIntValue(PreferencesEnum preferencesEnum) {
        return values.getInteger(preferencesEnum.name());
    }

    public static String getStringValue(PreferencesEnum preferencesEnum) {
        return values.getString(preferencesEnum.name());
    }

    public static boolean getBooleanValue(PreferencesEnum preferencesEnum) {
        return values.getBoolean(preferencesEnum.name());
    }

    public static float getFloatValue(PreferencesEnum preferencesEnum) {
        return values.getFloat(preferencesEnum.name());
    }

    public static long getLongValue(PreferencesEnum preferencesEnum) {
        return values.getLong(preferencesEnum.name());
    }
}