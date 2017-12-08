package Plant.State;
import Crop.Cropable;
import Plant.*;

public abstract class AbstrPlantState implements PlantState{
    Plant myPlant;

    public AbstrPlantState(Plant myPlant) {
        this.myPlant = myPlant;
    }
}
