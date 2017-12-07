package Crop.State;

import Base.ObserverTime;
import Crop.*;

public interface CropState  extends ObserverTime {

    public boolean isRipe();

    public  boolean isRotten();

    public boolean isRipen();
}
