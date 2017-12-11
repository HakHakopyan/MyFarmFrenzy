package plant;

import base.*;
import command.*;
import observer.ObserverSeason;
import observer.ObserverTime;

/**
 * Defines interface for Plants
 */
public interface Plantable extends ObserverSeason, ObserverTime, Representable, Generable, Commandable, Livable {

}
