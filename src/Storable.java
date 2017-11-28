public interface Storable<C extends Crop> {
    public  boolean isEmpty();
    public boolean addStored(C c);
}
