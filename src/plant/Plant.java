package plant;

import base.Costable;
import base.Generable;
import crop.*;
import plant.state.*;
import season.Season;

public abstract class Plant implements Plantable, Cloneable, Costable, Generable {
    protected PlantState myState;
    /**
     * Время жизни растения
     */
    public int myLifeTime;

    /**
     * стоимость растения
     */
    protected double myCost;

    /**
     * фактически устанавливает может ли растение выживать зимой, если Season.Winter то да
     */
    public Season season;

    /**
     * ссылка на экземпляр урожая
     */
    public Cropable myCrop;

    public PlantState myCropState = new StateCrop(this);
    public PlantState myIdleState = new StateIdle(this);

    /**
     * constructor - initializes the fields of the Plant
     * @param lifeTime время жизни
     * @param cost стоимость растения
     * @param season сезон
     */
    public Plant(int lifeTime, double cost, Season season) {
        this.myLifeTime = lifeTime;
        this.season = season;
        this.myCost = cost;
        changeState(new StateCrop(this));
    }

    /**
     * method for obtaining a reference to a crop instance from the heirs of the plant
     */
    public abstract void newCrop();

    public void changeState(PlantState state) {
        this.myState = state;
    }

    public void changeSeason(Season season) {
        this.myState.changeSeason(season);
    }

    public Cropable getCrop() {
        return this.myState.getCrop();
    }

    /**
     * Уменьшить срок жизни растения на одну единицу
     * @return true если у растения еще остались единицы жизни
     */
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

    /**
     * the method returns a reference to a new instance of the object
     * with the same internal state as the object whose method is called
     * @return reference to the Apple object
     * @throws CloneNotSupportedException
     */
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
