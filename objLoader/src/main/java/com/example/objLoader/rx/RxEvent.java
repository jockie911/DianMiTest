package com.example.objLoader.rx;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yc on 2017/5/31.
 */

public class RxEvent {

    public static final int EVENT_UPDATE = 1;
    public static final int EVENT_REMIND = 2;
    public static final int EVENT_MATCH_REFRESH = 3;

    @IntDef({EVENT_UPDATE, EVENT_REMIND, EVENT_MATCH_REFRESH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventType {
    }

    private int type;
    private Object[] data;

    public RxEvent(@EventType int type, Object... data) {
        this.type = type;
        this.data = data;
    }

    public
    @EventType int getType() {
        return type;
    }

    public Object getData() {
        return getData(0);
    }

    public Object getData(int index) {
        return index < data.length ? data[index] : null;
    }
}
