public class SimilarityChecker {
    static <T,V> boolean isSimilarity(T t, V v) {
        return  t.getClass().getName().equals(v.getClass().getName());
    }

    public static <T> String getClassName(T t) {
        return  t.getClass().getName();
    }

    public static <T> Class getClass(T t) {
        return t.getClass();
    }

    public static Class getClass(Rack rack) {

        return null;
}
}
