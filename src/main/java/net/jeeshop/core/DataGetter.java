package net.jeeshop.core;

/**
 * @author dylan
 * @date 16/3/22 21:32
 * Email: dinguangx@163.com
 */
public abstract class DataGetter<T> {
    public static enum DataType {
        LIST,STRING,LONG,DATE,DOUBLE,MAP,OBJECT
    }
    private DataType type;//list,string,long,date,double,map
    private String name;//数据项名称

    public DataGetter(String name, DataType type) {
        this.name = name;
        this.type = type;
    }

    public DataType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public abstract  T getData();
}
