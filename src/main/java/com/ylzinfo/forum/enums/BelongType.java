package com.ylzinfo.forum.enums;

public enum BelongType {
    QUESTION("提问"),
    SHARE("分享"),
    DISCUSS("讨论"),
    ADVICE("建议"),
    NOTICE("公告"),
    DYNAMIC("动态");

    private final String type;

    BelongType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
