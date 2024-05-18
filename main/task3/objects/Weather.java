package main.task3.objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import main.task3.enums.EBrightness;
import main.task3.enums.EWeatherModificator;
import main.task3.enums.EWeatherType;

@Getter
@Setter
public class Weather {
    private EBrightness brightness;
    private EWeatherType weatherType;
    private EWeatherModificator weatherModificator;
    private double temperature;

    @Setter(AccessLevel.NONE)
    private int mentalModification;

    public Weather(EBrightness brightness,
            EWeatherType weatherType,
            EWeatherModificator weatherModificator,
            double temperature
            ){
        this.brightness = brightness;

        this.weatherType = weatherType;
        if (this.getWeatherType().getValue() < this.getBrightness().getValue())
            throw new IllegalArgumentException("Invalid weather type");

        this.weatherModificator = weatherModificator;
        if (this.getWeatherModificator().getValue() < this.getWeatherType().getValue())
            throw new IllegalArgumentException("Invalid weather modificator");

        this.temperature = temperature;
        if (temperature <= 0 &&
                !(weatherType.equals(EWeatherType.SNOW) ||
                weatherType.equals(EWeatherType.CLEAR)))
            throw new IllegalArgumentException("Invalid weather type");
        else
            if (weatherType.equals(EWeatherType.SNOW))
                throw new IllegalArgumentException("Invalid weather type");

        this.mentalModification = brightness.getValue() +
                weatherModificator.getValue() +
                weatherType.getValue() +
                (temperature > 0 ? 1 : 0);
    }

    @Override
    public String toString() {
        return "was " + brightness.toString() + " " + weatherType.toString() + " " + weatherModificator.toString() + " day";
    }
}
