package Plant;

import Base.ObserverTime;
import Command.Command;
import Const.PlantConst;
import Crop.*;
import Crop.Fruit.Apple;
import Plant.State.PlantState;
import Plant.State.*;
import Season.Season;

public abstract class Plant implements Plantable, Cloneable {
    protected PlantState myState;
    /**
     * Время жизни растения
     */
    public int lifeTime;

    protected int cost;

    public Season season;

    public Cropable myCrop;

    public Plant(int lifeTime, int cost, Season season) {
        this.lifeTime = lifeTime;
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
    public Cropable getDelivery() {
        return this.myState.GetCrop();
    }

    public boolean reduceLifeTime() {
        this.lifeTime--;
        if (this.lifeTime > 0)
            return true;
        return false;
    }

    @Override
    public void updateTime() {
        this.myState.updateTime();
    }

    public void destroyCrop() {
        this.myCrop = null;
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

    public void doComand(Command com) {
        this.myState.doComand(com);
    }

    @Override
   public abstract Plantable clone() throws CloneNotSupportedException;
}
