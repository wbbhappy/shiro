package org.pan.bean;

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
