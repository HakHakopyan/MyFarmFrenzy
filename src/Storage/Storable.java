package Storage;
import Crop.*;

public interface Storable<C extends Crop> {
    public  boolean isEmpty();
    public boolean setStored(C c);
}
