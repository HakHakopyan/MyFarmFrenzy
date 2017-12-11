package base;

/**
 * defines an interface for objects that are able to live and end life as a result of certain internal processes
 * go to the state of death
 */
public interface Livable {
    /**
     * Is object Die ?
     * @return true if it Die - go to the state of die
     */
    public boolean isDie();
}
