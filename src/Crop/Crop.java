package Crop;

import Crop.State.CropState;
import Crop.State.StateRipen;
import Crop.State.StateRotten;
import Season.Season;
import Visitor.Visitor;

/**
 * crop [cra:p] - урожай
 */
public abstract class Crop implements Cropable {
    /**
     * срок хранения урожая
     */
    int shelfLife;
    /**
     * Время созревания урожая
     */
    int ripenTime;
    /**
     * Стоимость урожая
     */
    double myCost;

    /**
     * количество урожая
     */
    int count;

    protected CropState myState;

    protected Crop(int rTime, int sLife, int count, double cost) {
        this.ripenTime = rTime;
        this.shelfLife = sLife;
        setCount(count);
        this.myCost = cost;

        changeState(new StateRipen(this));
    }

    /**
     * rotten |ˈrɑːtn|  — гнилой, испорченный
     * @return true if end
     */
    @Override
    public  boolean isRotten() {
        return myState.isRotten();
    }
    /**
     * Урожай созрел ?
     * @return true if crop Rip
     */
    @Override
    public boolean isRipe() {
        return myState.isRipe();
    }

    /**
     * Урожай созреающий ?
     * @return true if crop Ripen
     */
    @Override
    public boolean isRipen() {
        return myState.isRipen();
    }

    /**
     * уменьшить время жизни
     */
    public boolean reduceShelfLife() {
        this.shelfLife--;
        if (this.shelfLife > 0)
            return true;
        return false;
    }

    /**
     * говорим плодам Созревай
     * фактиески уменьшаем Время созревания
     */
    public boolean doRipen() {
        this.ripenTime--;
        if (this.ripenTime > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void updateTime() {
        myState.updateTime();
    }

    public void changeState(CropState state) {
        this.myState = state;
    }

    @Override
    public double getCost() {
        return this.myCost;
    }

    @Override
    public void setCost(double newCost) {
        this.myCost = newCost;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public void setCount(int count) {
        if (count < 0)
            this.count = 0;
        this.count = count;
    }

    @Override
    public void acceptVisit(Visitor v) {
        v.visitCrop(this);
    }

    @Override
    public void changeSeason(Season season) {
        if (season.ordinal() >= Season.WINTER.ordinal()) {
            this.changeState(new StateRotten(this));
        }
    }
}
