package net.jeeshop.biz.member.enums;

/**
 * 性别
 * @author dylan
 * @date 16/3/26 22:43
 * Email: dinguangx@163.com
 */
public enum GenderType {
    Male(1), Female(2), Unknown(0);
    private int value;
    private GenderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GenderType parseValue(int value) {
        for(GenderType type : GenderType.values()) {
            if(type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
