package base;

/**
 * implements all objects who have Cost
 */
public interface Costable {

    /**
     * возвращает стоимость у объекта, у которого был вызван
     * @return object price
     */
    public double getCost();

    /**
     * устанавливает стоимость объекта, у которого метод был вызван
     * @param newCost устанавливаемая стоимость
     */
    public void setCost(double newCost);
}
