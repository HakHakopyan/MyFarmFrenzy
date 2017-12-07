package Crop;

import Base.Countable;
import Base.ObserverTime;
import Base.Costable;
import Visitor.Visitable;

public interface Cropable extends ObserverTime, Countable, Costable, Visitable {

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
