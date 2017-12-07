package Plant;

import Base.ObserverTime;
import Base.Seasonable;
import Command.Command;
import Crop.*;
import Plant.State.PlantState;
import Season.*;

public interface Plantable extends Seasonable, ObserverTime{

    //public void changeState(PlantState state);

    abstract public String getRepresentation();

    // Получить урожай
    public Cropable getDelivery();

    /**
     * урожай созрел ?
     */
    public boolean isCropReady();

    //public boolean isCropRotten();

    /**
     * Is Plant Die ?
     * @return true if Die
     */
    public boolean isDie();

    public void doComand(Command com);

    public void changeSeason(Season season);
}
