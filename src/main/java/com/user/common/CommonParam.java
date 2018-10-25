package com.user.common;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xuwenqian
 */
@Getter
@Setter
public class CommonParam {
    private static ThreadLocal<CommonParam> threadLocal = new ThreadLocal<CommonParam>();

    public static CommonParam getInstance() {
        //每个线程对应一个实例
        CommonParam param = threadLocal.get();
        if (param == null) {
            param = new CommonParam();
            threadLocal.set(param);
        }
        return param;
    }

    public static void remove() {
        threadLocal.remove();
    }


    /**************************************************************************************/
    private Long userId = -1L;
    private String clientType = "";
    private String aliasName;
    private String version;
    private String appId;

}
