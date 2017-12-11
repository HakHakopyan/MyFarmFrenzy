package visitor;

/**
 * The interface determines the possibility for an object to receive a visitor to collect information about the object
 */
public interface Visitable {
    /**
     * Accept Visitor and send him object reference
     * @param visitor contains reference to the Visitor object
     */
    public void acceptVisit(Visitor visitor);
}
