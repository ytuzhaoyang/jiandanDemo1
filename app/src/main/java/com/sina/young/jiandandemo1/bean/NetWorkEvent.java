package com.sina.young.jiandandemo1.bean;

/**
 * Created by zhaoyang on 16/8/18.
 *
 * 网络状态的实体类
 */
public class NetWorkEvent {
    public static final int AVAILABLE = 1;
    public static final int UNAVAILABLE = -1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public NetWorkEvent(int type) {
        this.type = type;
    }
}
