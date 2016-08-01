package net.jeeshop.biz.base.bean;

/**
 * @author dinguangx@163.com
 * @date 2015-12-21 23:37
 */
public class PageQueryBean
{
    private static final String ORDER_COLUMN = "order[0][column]";

    public static final int UNLIMIT_LENGTH = -1;
    public static final int DEFAULT_LENGTH = 10;
    private int start = 0;
    private int length = DEFAULT_LENGTH;


    public PageQueryBean() {
    }

    public PageQueryBean(int length) {
        this.length = length;
    }

    public PageQueryBean(int start, int length) {
        this.start = start;
        this.length = length;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "PageQueryBean{" +
                "start=" + start +
                ", length=" + length +
                '}';
    }
}
