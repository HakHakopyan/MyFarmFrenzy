package Storage;

import Visitor.Visitable;
import Crop.*;

public interface Storable<C extends Cropable> extends Visitable{

    public void doEmpty();

    public void store(C newCrop);
}
