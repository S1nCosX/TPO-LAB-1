package main.task3.enums;

import java.util.HashMap;
import java.util.Map;

public enum EWeatherModificator {
    SHADY(-3),
    WINDY(-2),
    WET(-1),
    DRY(0),
    SUNNY(1);

    int value;
    private static Map map = new HashMap<>();

    EWeatherModificator(int i) {
        this.value = i;
    }

    public static EMentalState valueOf(int i){
        return (EMentalState) map.get(i);
    }

    public int getValue() {
        return value;
    }
}
