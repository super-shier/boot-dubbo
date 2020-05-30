package com.li.yun.biao.job.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * @author: liyunbiao
 * @date: 2020/5/28 10:43 上午
 * @description
 */
public class ThrowableUtil {

    /**
     * parse error to string
     *
     * @param e
     * @return
     */
    public static String toString(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        String errorMsg = stringWriter.toString();
        return errorMsg;
    }

}
