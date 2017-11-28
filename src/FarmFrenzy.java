import java.util.function.Function;

// Yielding - дающий урожай
public class FarmFrenzy {
    public static void main(String[] args) {
        System.out.println("Farm Frenzy");

        /*
        Arable<Cost.Cost> arable = new Arable<>();
        arable.setT(new Generator.Plant.Herb.Wheat());
        System.out.println(arable.getT().getClass());
        */
        Integer x = 2;
        System.out.println(convert((q)->q * 3, x));
    }

    static  <T> T convert (Function<T, T> map, T t) {
        return  map.apply(t);
    }
}
