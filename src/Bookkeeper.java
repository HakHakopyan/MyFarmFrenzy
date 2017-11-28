import Storage.Storage;

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
    /*
    public int calculateCost(Storage.Rack rack) {
        int cost = 0;
        if (rack != null) {
            Storage.Rack rackBuf = new Storage.Rack(rack.getShelfMaxCount());
            //Set<> s;
        }

        return  cost;
    }
    */
    public  <C extends Crop> int calculateCost(C c) {
        return c.getCost();
    }
}
