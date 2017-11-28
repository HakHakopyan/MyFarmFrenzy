package Storage;
import Crop.*;
import Checker.*;
import java.util.Stack;

public class Rack <C extends Crop> implements Storable<C> {
    /**
     * Max count of shelf
     */
    int shelfMaxCount;
    /**
     * Contais objects -> our crops
     */
    Stack<C> shelfs;
    /**
     * The name of objects that are on the shelves
     * all objects of the same
     */
    String name = "";
    /**
     * count of shelves that are used
     */
    int shelfCount;

    public Rack(int shelfMaxCountCount) {

        this.shelfs = new Stack<>();
        this.shelfs.clear();
        this.shelfMaxCount = shelfMaxCountCount;
    }

    public  boolean isEmpty() {
        return this.shelfs.isEmpty();
    }

    public  int getShelfMaxCount() {
        return shelfMaxCount;
    }

    public C getStored() {
        if (isEmpty())
            return null;
        return shelfs.pop();
    }

    public C getStored(int getCount) {
        if (isEmpty())
            return null;
        return shelfs.pop();
    }

    @Override
    public boolean addStored(C c) {
        if (c == null ) {
            return false;
        }
        if (shelfs.empty()) {
            name = SimilarityChecker.getClassName(c);
        }
        if (this.shelfCount < this.shelfMaxCount) {
            shelfs.push(c);
            return true;
        }
        return false;
    }
}
