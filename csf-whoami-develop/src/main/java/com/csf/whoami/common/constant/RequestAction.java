package com.csf.whoami.common.constant;

import lombok.Getter;
import lombok.Setter;

public enum RequestAction {

    NONE("none"),
    COMPLETE("none"),
    REDIRECT("none"),
    ADD("add"),
    UPDATE("change"),
    DELETE("delete"),
    SELECT("select");

    @Setter
    @Getter
    private String value;

    RequestAction(String value) {
        this.value = value;
    }
}
