import java.util.Stack;

public class Rack <C extends Crop> implements Storable<C> {
    int shelfCount;
    Stack<C> shelfs;
    String name = "";
    int count;

    public Rack(int shelfCount) {

        this.shelfs = new Stack<>();
        this.shelfs.clear();
        this.shelfCount = shelfCount;
    }

    public  boolean isEmpty() {
        return this.shelfs.isEmpty();
    }

    public  int getShelfCount() {
        return  shelfCount;
    }

    public C getDelivery() {
        if (isEmpty())
            return null;
        return shelfs.pop();
    }

    @Override
    public boolean addStored(C t) {
        if (t == null )
        return false;
        if (shelfs.empty())
        name = SimilarityChecker.getClassName(t);
        return false;
    }


}
