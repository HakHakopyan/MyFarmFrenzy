/**
 * Наследуются все классы ,которые имеют стоимость
 */
public abstract class Cost {
    protected final  int COST;
    protected int time = 0;

    protected Cost(int cost) {
        this.COST = cost;
    }

    public  int getCost() {
        return this.COST;
    }
}
