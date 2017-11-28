package Base.Plant.Herb;
import Season.*;
import Base.Plant.*;
/**
 * In general use, herbs are any plants used for food, flavoring, medicine, or fragrances.
 */
public abstract class Herb extends Plant {

    Herb(int readyTime, Season season) {
        super(readyTime, season);
    }
}
