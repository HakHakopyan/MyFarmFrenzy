package Plant.State;
import Base.Genarable;
import Command.Commandable;
import Observer.ObserverSeason;
import Base.Representable;

import Observer.ObserverTime;

public interface PlantState extends ObserverTime, ObserverSeason, Representable, Genarable, Commandable {
    public boolean isDie();
}
