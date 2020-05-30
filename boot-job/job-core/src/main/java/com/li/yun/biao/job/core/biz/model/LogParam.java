package com.li.yun.biao.job.core.biz.model;

import java.io.Serializable;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 8:33 下午
 * @description
 */
public class LogParam implements Serializable {
    private static final long serialVersionUID = 42L;
    private long logDateTim;
    private long logId;
    private int fromLineNum;

    public LogParam() {
    }

    public LogParam(long logDateTim, long logId, int fromLineNum) {
        this.logDateTim = logDateTim;
        this.logId = logId;
        this.fromLineNum = fromLineNum;
    }

    public long getLogDateTim() {
        return logDateTim;
    }

    public void setLogDateTim(long logDateTim) {
        this.logDateTim = logDateTim;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public int getFromLineNum() {
        return fromLineNum;
    }

    public void setFromLineNum(int fromLineNum) {
        this.fromLineNum = fromLineNum;
    }
}