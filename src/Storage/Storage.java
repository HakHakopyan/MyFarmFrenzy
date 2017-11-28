package Storage;

import java.util.ArrayList;
import java.util.List;
import Crop.*;

public class Storage{
    private List<Rack> racks;
    private int rackCount;

    public Storage(int rackCount) {
        racks = new ArrayList<>();
        this.rackCount = rackCount;
    }

    public <C extends Crop> boolean setCrop(C c) {
        return  false;
    }

    public Crop getCrop() {
        return null;
    }

    public int getRackCount() {
        return racks.size();
    }

    public  StorageBook getStorageBook() {
        StorageBook book = new StorageBook();
        for (Rack rack:racks) {
            Crop crop = rack.getStored();
            book.setRecord(new Record(rack.getSroredName(), rack.getShelfCount(), crop.getCost()));
            rack.setStored(crop);
        }

        return  book;
    }

}
