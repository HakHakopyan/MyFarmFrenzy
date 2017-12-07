package Visitor;

import Crop.Cropable;
import Storage.Storable;

public interface Visitor {
    public void visitStorage(Storable storage);

    public void visitCrop(Cropable crop);
}
