package com.csf.whoami.common.constant;

import lombok.Getter;
import lombok.Setter;

public enum ForwardAction {

    SUCCESS("success"),
    REDIRECT("forward"),
    EXECUTE("execute"),
    ERROR("error");

    @Setter
    @Getter
    private String value;

    ForwardAction(String value) {
        this.value = value;
    }
}
