package visitor;

import crop.Cropable;
import storage.Storable;

public class VisitorTotalCost implements Visitor {
    /**
     * contains total cost - the result of adding the values of all objects which are visited
     */
    int totalCost = 0;

    public void visitStorage(Storable storage) {
    }

    public void visitCrop(Cropable crop) {
        this.totalCost += crop.getCost()*crop.getCount();
    }

    public int getValue() {
        return totalCost;
    }
}
