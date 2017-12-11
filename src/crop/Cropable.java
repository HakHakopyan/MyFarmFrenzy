package crop;

import base.*;
import command.Commandable;
import observer.ObserverSeason;
import observer.ObserverTime;
import visitor.Visitable;

public interface Cropable extends ObserverTime, ObserverSeason, Countable, Costable, Visitable, Representable, Commandable {

    /**
     * rotten |ˈrɑːtn|  — гнилой, испорченный
     * @return true if end
     */
    public  boolean isRotten();

    /**
     * Урожай созрел ?
     * @return true if crop Rip
     */
    public boolean isRipe();

    /**
     * Урожай созревающий ?
     * @return true if crop Ripen
     */
    public boolean isRipen();
}
