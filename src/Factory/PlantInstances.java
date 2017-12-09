package Factory;

import Const.PlantConst;
import Plant.Tree.AppleTree;
import Annotations.*;

/**
 * class Plants contain the methods which give the Plants instance
 */
@What("Plant")
@GetPlant
public class PlantInstances {

    /**
     * this method return AppleTree instance
     * @return AppleTree instance
     */
    @GetPlant
    @What("AppleTree")
    AppleTree getAppleTree() {
        return new AppleTree(PlantConst.APPLE_LT, PlantConst.APPLE_COST);
    }
}
