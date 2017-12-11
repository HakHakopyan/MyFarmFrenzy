package crop;

import command.Command;
import crop.state.CropState;
import crop.state.StateRipen;
import crop.state.StateRotten;
import season.Season;
import visitor.Visitor;

import java.util.List;

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

    /**
     * Состояние Урожая
     */
    protected CropState myState;

    /**
     * Конструктор урожая, инициализирует параметры находящиеся в абстрактном классе Crop
     * @param rTime время созревания
     * @param sLife время хранения
     * @param count количество урожая, даваемого растением за один цикл созревания
     * @param cost стоимость единицы урожая
     */
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
     * @return false if crop is already ripe
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
     * @return fasle if the crop is already ripe
     */
    public boolean doRipen() {
        this.ripenTime--;
        if (this.ripenTime > 0) {
            return true;
        }
        return false;
    }

    /**
     * Добавляем единицу вермени для урожая, чтобы он созревал...
     */
    @Override
    public void updateTime() {
        myState.updateTime();
    }

    /**
     * меняет состояние урожая
     * @param state содержит состояние
     */
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
    public void acceptVisit(Visitor visitor) {
        visitor.visitCrop(this);
    }

    /**
     * change Season for crop? if season == Winter crop is rope
     * @param season contain season name
     */
    @Override
    public void changeSeason(Season season) {
        if (season.ordinal() >= Season.WINTER.ordinal()) {
            this.changeState(new StateRotten(this));
        }
    }

    /**
     * sends commands that change the number of apples
     * @param commandList содержит список камонд, которые нужно выполнить для данного объекта
     */
    @Override
    public void doCommand(List<Command> commandList) {
        this.myState.doCommand(commandList);
    }
}
