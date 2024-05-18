package main.task3.enums;

import java.util.HashMap;
import java.util.Map;

public enum EWeatherType {
    SNOW(-2),
    HEAVY_RAIN(-1),
    RAIN(0),
    MIST(1),
    CLEAR(2);

    int value;
    private static Map<Integer, EWeatherType> map = new HashMap<>();

    EWeatherType(int i) {
        this.value = i;
    }

    public static EWeatherType valueOf(int i){
        return map.get(i);
    }

    public int getValue() {
        return value;
    }
}
