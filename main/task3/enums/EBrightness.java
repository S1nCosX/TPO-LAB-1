package main.task3.enums;

import java.util.HashMap;
import java.util.Map;

public enum EBrightness {
    BRIGHT(2),
    SLIGHTLY_BRIGHT(1),
    NORMAL(0),
    SHADY(-1),
    DARKNESS(2);

    int value;
    private static Map map = new HashMap<>();

    EBrightness(int i) {
        this.value = i;
    }

    public static EMentalState valueOf(int i){
        return (EMentalState) map.get(i);
    }

    public int getValue() {
        return value;
    }
}
