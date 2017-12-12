package base;

import crop.*;

/**
 * getCrop() -  Factoriable Method;
 */
public interface Generable {
    /**
     * get a crop
     * @return a reference to an instance of a crop heir
     */
    //@What(description = "Only crop inheritor")
    public abstract Cropable getCrop();

    /**
     * is Crop Ready )
     * @return true f crop Ripe (ready to collect)
     */
    public abstract boolean isCropReady();
}
