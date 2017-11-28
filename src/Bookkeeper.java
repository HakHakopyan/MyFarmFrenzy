import Crop.Crop;
import Storage.*;

/**
 * Bookkeeper |ˈbʊkkiːpər|  — бухгалтер, счетовод
 */
public class Bookkeeper {

    public static int calculateTotalCoct(Storage storage) {
        int totalCost = 0;
        StorageBook book = storage.getStorageBook();
        while (!book.isEmpty()) {
            Record record = book.getRecord();
            totalCost += record.getCount()*record.getCost();
        }

        return  totalCost;
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
