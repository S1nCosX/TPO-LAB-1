package main.task3.enums;

import java.util.HashMap;
import java.util.Map;

public enum EMentalState {
    INSANE(-2),
    MAD(-1),
    SANE(0),
    GOOD(1),
    HAPPY(2);

    int value;
    private static Map <Integer, EMentalState> map = new HashMap<>();

    EMentalState(int i) {
        this.value = i;
    }
    static {
        for (EMentalState e: values()) {
            map.put(e.value, e);
        }
    }

    public static EMentalState valueOf(int i){
        return map.get(i);
    }

    public int getValue() {
        return value;
    }
}
