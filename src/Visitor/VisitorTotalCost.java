package Visitor;

import Crop.Cropable;
import Storage.Storable;

public class VisitorTotalCost implements Visitor {
    int totalCost;

    public VisitorTotalCost() {
        this.totalCost = 0;
    }

    public void visitStorage(Storable storage) {
    }

    public void visitCrop(Cropable crop) {
        this.totalCost += crop.getCost()*crop.getCount();
    }

    public int getValue() {
        return totalCost;
    }
}
