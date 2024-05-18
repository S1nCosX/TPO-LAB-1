package tests.test3;

import main.task3.enums.EBrightness;
import main.task3.enums.ECreatureType;
import main.task3.enums.EWeatherModificator;
import main.task3.enums.EWeatherType;
import main.task3.objects.Creature;
import main.task3.objects.Location;
import main.task3.objects.Weather;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Tests {
    @Test
    @DisplayName("Location simulation testing")
    void LocationSimulationTest(){
        Location location = new Location(
                "Weathering heights",
                "The storm was approaching this menacing mansion",
                new Weather(
                        EBrightness.SHADY,
                        EWeatherType.HEAVY_RAIN,
                        EWeatherModificator.WET,
                        5
                ),
                new ArrayList<Creature>(List.of(new Creature[]{
                        new Creature(
                                "Heatcliff",
                                ECreatureType.HUMAN,
                                "Brave young man"
                        ),
                        new Creature(
                                "Katty",
                                ECreatureType.GHOST,
                                "Ghost of Heatcliff's love"
                        )
                })
                )
        );
        assertEquals(location.simulate(), "Heatcliff going insane bacause of was SHADY HEAVY_RAIN WET day\n" +
                "Katty flowing in air to Heatcliff\n" +
                "Heatcliff going insane bacause of Katty\n" +
                "Heatcliff running from Katty\n");
    }
    @Test
    @DisplayName("Location Creature custom movement testing")
    void LocationCustomMovementTest(){
        Location location = new Location(
                "Weathering heights",
                "The storm was approaching this menacing mansion",
                new Weather(
                        EBrightness.SHADY,
                        EWeatherType.HEAVY_RAIN,
                        EWeatherModificator.WET,
                        5
                ),
                new ArrayList<Creature>(List.of(new Creature[]{
                        new Creature(
                                "Heatcliff",
                                ECreatureType.HUMAN,
                                "Brave young man",
                                "Falling from cliff"
                        ),
                        new Creature(
                                "Katty",
                                ECreatureType.GHOST,
                                "Ghost of Heatcliff's love",
                                "aproaching"
                        )
                })
                )
        );
        assertEquals(location.simulate(), "Heatcliff going insane bacause of was SHADY HEAVY_RAIN WET day\n" +
                "Katty aproaching to Heatcliff\n" +
                "Heatcliff going insane bacause of Katty\n" +
                "Heatcliff Falling from cliff from Katty\n");
    }
}
