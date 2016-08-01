package net.jeeshop.core.util.transform;

public interface Transform<T, D> {
    /**
     * data convert
     * @param t
     * @return
     */
    D convert(T t);

    /**
     * is applied
     * @param t
     * @return
     */
    boolean apply(T t);
}