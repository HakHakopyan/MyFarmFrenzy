package Plant.State;
import Base.Genarable;
import Observer.ObserverSeason;
import Base.Representable;

import Observer.ObserverTime;

public interface PlantState extends ObserverTime, ObserverSeason, Representable, Genarable {
    public boolean isDie();
}
