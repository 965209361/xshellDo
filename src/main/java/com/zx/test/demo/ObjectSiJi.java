package com.zx.test.demo;

/**
 * created by zengqintao on 2018-06-07 11:29 .
 **/
public enum ObjectSiJi {
    spring("春天", 0), summer("夏天", 1), autumn("秋天", 2), winter("冬天", 3);
    private String desc;

    private int index;

    public String getDesc() {
        return desc;
    }

    public int getIndex() {
        return index;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private ObjectSiJi(String desc, int index) {
        this.desc = desc;
        this.index = index;
    }

    public static String getDesc(int index) {
        for (ObjectSiJi is : ObjectSiJi.values()) {
            if (is.getIndex() == index) {
                return is.getDesc();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        getDesc(1);
    }
}
