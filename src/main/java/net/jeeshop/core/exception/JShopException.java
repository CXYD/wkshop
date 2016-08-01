package net.jeeshop.core.exception;

/**
 * @author dinguangx@163.com
 * @date 2015-12-21 23:56
 */
public class JShopException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JShopException() {
        super();
    }
    /**
     * @param msg
     */
    public JShopException(String msg) {
        super(msg);
    }

    public JShopException(String message, Throwable cause) {
        super(message, cause);
    }

    public JShopException(Throwable cause) {
        super(cause);
    }
}
