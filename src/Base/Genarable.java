package Base;

import Crop.*;

/**
 * getCrop() -  Factoriable Method;
 */
public interface Genarable {
    //@What(description = "Only Crop inheritor")
    Cropable getCrop();

    boolean isCropReady();
}
