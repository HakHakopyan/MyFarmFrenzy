package Observer;

import java.util.Observable;

public class MyExternalObservable extends Observable {
    public MyExternalObservable() {
        super();
        this.setChanged();
    }

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

}
