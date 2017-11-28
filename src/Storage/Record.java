package Storage;

import java.math.BigDecimal;

public class Record {
    String name;
    int count;
    int cost;

    public Record(String name, int count, int cost) {
        this.name = name;
        this.count = count;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }
    public int getCount() {
        return count;
    }

    public int getCost() {
        return cost;
    }
}
