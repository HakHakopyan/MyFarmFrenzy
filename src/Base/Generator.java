package Base;

/**
 * Factoriable method
 */
public abstract class Generator implements Genarable, ObserverTime {
    protected final  int readyTime;
    protected int timer = 0;

    protected Generator(int readyTime) {
        this.readyTime = readyTime;
    }
}

