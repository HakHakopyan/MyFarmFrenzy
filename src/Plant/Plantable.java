package Plant;

import Base.*;
import Command.*;
import Crop.*;
import Observer.ObserverSeason;
import Observer.ObserverTime;
import Season.*;

public interface Plantable extends ObserverSeason, ObserverTime, Representable, Genarable, Commandable {

    //public void changeState(PlantState state);

    abstract public String getRepresentation();

    // Получить урожай
    public Cropable getCrop();

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

    public void changeSeason(Season season);
}
