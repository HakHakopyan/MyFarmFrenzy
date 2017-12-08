package Crop;

import Base.*;
import Observer.ObserverSeason;
import Observer.ObserverTime;
import Visitor.Visitable;

public interface Cropable extends ObserverTime, ObserverSeason, Countable, Costable, Visitable, Representable {

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
