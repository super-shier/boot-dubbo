package com.li.yun.biao.job.core.glue;


import java.util.Arrays;
import java.util.Objects;

/**
 * @author: liyunbiao
 * @date: 2020/5/28 10:43 上午
 * @description
 */
public enum GlueTypeEnum {

    BEAN("BEAN", false, null, null),
    GLUE_GROOVY("GLUE(Java)", false, null, null),
    GLUE_SHELL("GLUE(Shell)", true, "bash", ".sh"),
    GLUE_PYTHON("GLUE(Python)", true, "python", ".py"),
    GLUE_PHP("GLUE(PHP)", true, "php", ".php"),
    GLUE_NODEJS("GLUE(Nodejs)", true, "node", ".js"),
    GLUE_POWERSHELL("GLUE(PowerShell)", true, "powershell", ".ps1");

    GlueTypeEnum(String desc, boolean isScript, String cmd, String suffix) {
        this.desc = desc;
        this.isScript = isScript;
        this.cmd = cmd;
        this.suffix = suffix;
    }

    private String desc;
    private boolean isScript;
    private String cmd;
    private String suffix;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isScript() {
        return isScript;
    }

    public void setScript(boolean script) {
        isScript = script;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public static GlueTypeEnum match(String name) {
        return Arrays.stream(values()).filter(item -> Objects.equals(item.name(), name)).findFirst().orElse(null);
    }

}
