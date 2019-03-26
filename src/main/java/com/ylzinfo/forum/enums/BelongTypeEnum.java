package com.ylzinfo.forum.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum BelongTypeEnum implements IEnum<String> {
    提问("QUESTION"),
    分享("SHARE"),
    讨论("DISCUSS"),
    建议("ADVICE"),
    公告("NOTICE"),
    动态("DYNAMIC");

    private String value;

    BelongTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
