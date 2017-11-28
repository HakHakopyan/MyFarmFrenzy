package Storage;
import Crop.*;
import Checker.*;
import java.util.Stack;

public class Rack <C extends Crop> implements Storable<C> {
    /**
     * Max count of shelf
     */
    private int shelfMaxCount;
    /**
     * Contais objects -> our crops
     */
    private Stack<C> shelfs;
    /**
     * The storedName of objects that are on the shelves
     * all objects of the same
     */
    private String storedName = "";

    public int getShelfCount() {
        return shelfCount;
    }

    /**
     * count of shelves that are used
     */
    private int shelfCount;

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

    public String getSroredName() {
        return storedName;
    }

    @Override
    public boolean setStored(C c) {
        if (c == null ) {
            return false;
        }
        if (shelfs.empty()) {
            storedName = SimilarityChecker.getClassName(c);
        }
        if (this.shelfCount < this.shelfMaxCount) {
            shelfs.push(c);
            shelfCount++;
            return true;
        }

        return false;
    }
}
