package visitor;

import crop.Cropable;
import storage.Storable;

/**
 * The interface determines the methods of the visitor
 */
public interface Visitor {
    /**
     * in the method, the actions are performed on the object to collect information about it
     * @param storage contains link to the object with interface {@link Storable Storable}
     */
    public void visitStorage(Storable storage);

    /**
     * in the method, the actions are performed on the object to collect information about it
     * @param crop contains link to the object with interface {@link Cropable Cropable}
     */
    public void visitCrop(Cropable crop);

    /**
     * method returns the result of the work of the visitor
     * @return result of visit
     */
    public int getValue();
}
