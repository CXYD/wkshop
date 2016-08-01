package net.jeeshop.core.util.transform;

/**
 * @author dinguangx@163.com
 * @date 11/23/15 2:34 PM
 */
public abstract class AbstractTransform<T, D> implements Transform<T, D> {
    @Override
    public boolean apply(T t) {
        return true;
    }
}
