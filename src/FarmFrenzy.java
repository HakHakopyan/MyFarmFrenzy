import java.util.function.Function;
import Crop.*;
import Crop.Cereal.Millet;

// Yielding - дающий урожай
public class FarmFrenzy {
    public static void main(String[] args) {
        System.out.println("Farm Frenzy");

        /*
        Arable<Cost.Cost.Cost.Cost> arable = new Arable<>();
        arable.setT(new Base.Generator.Base.Plant.Base.Plant.Base.Plant.Base.Plant.Herb.Herb.Base.Plant.Herb.Wheat());
        System.out.println(arable.getT().getClass());
        */
        Integer x = 2;
        System.out.println(convert((q)->q * 3, x));

        Crop cr;
        Millet m = new Millet();
        cr = m;
        System.out.println(cr.getClass().getName());
    }

    static  <T> T convert (Function<T, T> map, T t) {
        return  map.apply(t);
    }
}
