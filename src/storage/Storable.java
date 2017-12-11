package storage;

import visitor.Visitable;
import crop.*;

public interface Storable<C extends Cropable> extends Visitable{

    /**
     * free the warehouse from the accumulated crop, make it empty
     */
    public void doEmpty();

    /**
     * method stores the link to the crop object in the object with th interface {@link Storable Storable}
     * @param newCrop
     */
    public void store(C newCrop);
}
