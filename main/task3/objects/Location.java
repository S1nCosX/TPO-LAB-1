package main.task3.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.task3.enums.ECreatureType;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String name;
    private String description;
    private Weather weather;
    private ArrayList<Creature> creatures;

    public String simulate(){
        Creature target = null;
        String str = "";
        for (Creature creature : creatures) {
            if (creature.getCreatureType().equals(ECreatureType.HUMAN)) {
                target = creature;
                str += target.setMentalState(
                        getWeather().getMentalModification(),
                        getWeather()
                ) + "\n";
            }
            else{
                assert target != null;
                str += creature.moveTo(target) + "\n";
                str += target.setMentalState(-1, creature) + "\n";
            }
        }
        return str;
    }
}
