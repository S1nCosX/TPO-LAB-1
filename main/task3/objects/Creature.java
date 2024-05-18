package main.task3.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import main.task3.enums.ECreatureType;
import main.task3.enums.EMentalState;

import java.util.Objects;

import static java.lang.Math.min;

@Getter
@Setter
public class Creature {
    private String name;
    private ECreatureType creatureType;
    private String description;
    private String movement;
    private EMentalState mentalState;

    public Creature(String name, ECreatureType creatureType, String description, String movement) {
        this.name = name;
        this.creatureType = creatureType;
        this.description = description;
        this.movement = movement;
        this.mentalState = EMentalState.SANE;
    }

    public Creature(String name, ECreatureType creatureType, String description) {
        this.name = name;
        this.creatureType = creatureType;
        this.description = description;
        this.mentalState = EMentalState.SANE;
    }

    String move(){
        if (Objects.equals(this.getMovement(), "")){
            switch (creatureType) {
                case GHOST -> this.setMovement("flowing in air");
                case HUMAN -> this.setMovement("walking");
                case SHADOW -> this.setMovement("frighteningly shaking");
            }
        }
        return (name + movement);
    }

    String moveTo(Creature o){
        if (Objects.equals(this.getMovement(), null)){
            switch (creatureType) {
                case GHOST -> this.setMovement("flowing in air");
                case HUMAN -> {
                    if (getMentalState().getValue() != -2) {
                        this.setMovement("walking");
                    } else {
                        this.setMovement("running");
                    }
                }
                case SHADOW -> this.setMovement("frighteningly shaking");
            }
        }
        return (this.getName() + " " + this.getMovement() + " to " + o.getName());
    }

    String moveFrom(Creature o){
        if (Objects.equals(this.getMovement(), null)){
            switch (creatureType) {
                case GHOST -> this.setMovement("flowing in air");
                case HUMAN -> {
                    if (getMentalState().getValue() != -2) {
                        this.setMovement("walking");
                    } else {
                        this.setMovement("running");
                    }
                }
                case SHADOW -> this.setMovement("frighteningly shaking");
            }
        }
        return (this.getName() + " " + this.getMovement() + " from " + o.getName());
    }

    public String setMentalState(int modify, Object o) {
        if (this.getMentalState().getValue() + modify < -2){
            setMentalState(EMentalState.INSANE);
        }
        else{
            setMentalState(
                    EMentalState.valueOf(
                            min(this.getMentalState().getValue() + modify, 2)
                    )
            );
        }
        return getMentalState().getValue() == -2 ?
                this.getName() + " going insane bacause of " + o.toString() +
                        (
                                o.getClass().equals(Creature.class) ?
                                "\n" + moveFrom((Creature) o) : ""
                        )
                : "";
    }

    @Override
    public String toString() {
        return name;
    }
}
