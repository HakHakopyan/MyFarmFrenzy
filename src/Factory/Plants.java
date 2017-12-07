package Factory;

import Const.PlantConst;
import Plant.Tree.AppleTree;
import Annotations.*;

@What("Plant")
public class Plants {

    @GetPlant
    @What("AppleTree")
    AppleTree getAppleTree() {
        return new AppleTree(PlantConst.APPLE_LT, PlantConst.APPLE_COST);
    }
}
