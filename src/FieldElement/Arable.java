package FieldElement;

import Plant.Plantable;

import java.util.Observable;
import java.util.Observer;

public interface Arable extends Plantable, Observer{

    public boolean plantExist();

    public void setPlant(String plantName);

    public void watchFor(Observable ob);
}
