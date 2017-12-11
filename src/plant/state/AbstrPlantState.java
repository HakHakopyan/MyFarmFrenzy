package plant.state;
import plant.*;

public abstract class AbstrPlantState implements PlantState{
    /**
     * stores a reference to an instance of the heir Plant for which the state is determined
     */
    Plant myPlant;

    /**
     * initializes a field, stores link
     * @param myPlant reference to an instance of the heir Plant
     */
    public AbstrPlantState(Plant myPlant) {
        this.myPlant = myPlant;
    }
}
