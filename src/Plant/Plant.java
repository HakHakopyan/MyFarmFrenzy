package Plant;

import Base.Costable;
import Command.Command;
import Crop.*;
import Plant.State.PlantState;
import Plant.State.*;
import Season.Season;

public abstract class Plant implements Plantable, Cloneable, Costable {
    protected PlantState myState;
    /**
     * Время жизни растения
     */
    public int myLifeTime;

    protected double myCost;

    public Season season;

    public Cropable myCrop;

    public Plant(int lifeTime, double cost, Season season) {
        this.myLifeTime = lifeTime;
        this.season = season;
        this.myCost = cost;
        changeState(new StateCrop(this));
    }

    abstract public  void newCrop();

    public void changeState(PlantState state) {
        this.myState = state;
    }

    public void changeSeason(Season season) {
        this.myState.changeSeason(season);
    }

    // Получить урожай
    public Cropable getCrop() {
        return this.myState.getCrop();
    }

    public boolean reduceLifeTime() {
        this.myLifeTime--;
        if (this.myLifeTime > 0)
            return true;
        return false;
    }

    @Override
    public void updateTime() {
        this.myState.updateTime();
    }

    public void destroyCrop() {
        this.myCrop.changeSeason(Season.WINTER);
    }

    /**
     * урожай созрел ?
     */
    public boolean isCropReady() {
        return this.myState.isCropReady();
    }

    public boolean isCropRotten() {
        return this.myCrop.isRotten();
    }

    public boolean isDie() {
        return this.myState.isDie();
    }

    @Override
   public abstract Plantable clone() throws CloneNotSupportedException;

    @Override
    public double getCost() {
        return myCost;
    }

    @Override
    public void setCost(double newCost) {
        this.myCost = newCost;
    }
}
