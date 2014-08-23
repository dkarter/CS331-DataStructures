/**
 *
 * @author Dor
 */
public interface Iterator<E extends Comparable<E>> {
    E get();
    void next();
    boolean isValid();
}