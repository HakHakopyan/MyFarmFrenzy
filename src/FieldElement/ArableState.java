package FieldElement;

import Plant.Plantable;

public interface ArableState extends Plantable {

    public void setPlant(String plantName);

    public boolean plantExist();

//    public void changeState(ArableState newState);

}
