package crop.state;

import command.Commandable;
import observer.ObserverSeason;
import observer.ObserverTime;
import base.Representable;

public interface CropState  extends ObserverTime, ObserverSeason, Representable, Commandable {

    /**
     * is crop already ripe
     * @return true if crop already ripe
     */
    public boolean isRipe();

    /**
     * is crop already rotten
     * @return true if crop already rotten
     */
    public  boolean isRotten();

    /**
     * the crop is in the process of maturation
     * @return true if crop is in process of maturation
     */
    public boolean isRipen();
}
