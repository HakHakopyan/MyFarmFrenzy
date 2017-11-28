/**
 * Bookkeeper |ˈbʊkkiːpər|  — бухгалтер, счетовод
 */
public class Bookkeeper {

    public int calculateCoct(Storage storage) {
        int rackCount = storage.getRackCount();
        int cost = 0;
        for (int i = 1; i <= rackCount; i++) {

        }

        return  cost;
    }

    public int calculateCost(Rack rack) {
        int cost = 0;
        if (rack != null) {
            Rack rackBuf = new Rack(rack.getShelfCount());
            //Set<> s;
        }

        return  cost;
    }
    public  <C extends Crop> int calculateCost(C c) {
        return c.getCost();
    }
}
