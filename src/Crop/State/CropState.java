package Crop.State;

import Command.Commandable;
import Observer.ObserverSeason;
import Observer.ObserverTime;
import Base.Representable;

public interface CropState  extends ObserverTime, ObserverSeason, Representable, Commandable {

    public boolean isRipe();

    public  boolean isRotten();

    public boolean isRipen();
}
