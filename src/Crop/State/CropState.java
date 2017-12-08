package Crop.State;

import Observer.ObserverSeason;
import Observer.ObserverTime;
import Base.Representable;

public interface CropState  extends ObserverTime, ObserverSeason, Representable {

    public boolean isRipe();

    public  boolean isRotten();

    public boolean isRipen();
}
