package factory;

import constants.*;
import plant.tree.AppleTree;
import annotations.*;
import plant.vegetables.CucumberBush;
import plant.vegetables.TomatoBush;

/**
 * class Plants contain the methods which give the Plants instance
 */
@What("plant")
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

    /**
     * this method return CucumberBush instance
     * @return CucumberBush instance
     */
    @GetPlant
    @What("CucumberBush")
    CucumberBush getCucumberBush() {
        return new CucumberBush(PlantConst.CUCUMBER_LT, PlantConst.CUCUMBER_COST);
    }

    /**
     * this method return TomatoBush instance
     * @return TomatoBush instance
     */
    @GetPlant
    @What("TomatoBush")
    TomatoBush getTomatoBush() {
        return new TomatoBush(PlantConst.TOMATO_LT, PlantConst.TOMATO_COST);
    }

}
