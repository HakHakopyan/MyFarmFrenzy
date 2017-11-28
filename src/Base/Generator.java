package Base;

import Crop.Crop;

/**
 * Factory method
 */
public abstract class Generator implements Genarable {
    protected final  int READY_TYME;
    protected int timer = 0;

    protected Generator(int readyTime) {
        this.READY_TYME = readyTime;
    }

    @Override
    abstract public void addTime();
    @Override
    abstract public Crop getDelivery();
    @Override
    abstract  public Boolean deliveryIsPossible();
}

