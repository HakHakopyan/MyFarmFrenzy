package Storage;

import java.util.ArrayList;
import java.util.List;
import Crop.*;

public class Storage{
    private List<Rack> racks;
    private int count;

    public Storage(int count) {
        racks = new ArrayList<>();
        this.count = count;
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



}
