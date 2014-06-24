package org.pan.bean;

/**
 * Created by panmingzhi on 2014/6/25.
 */
public class Permission {
    private String premissionName;

    public Permission(String premissionName) {
        this.premissionName = premissionName;
    }

    public String getPremissionName() {
        return premissionName;
    }

    public void setPremissionName(String premissionName) {
        this.premissionName = premissionName;
    }
}
