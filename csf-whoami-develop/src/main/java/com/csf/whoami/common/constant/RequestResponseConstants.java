/**
 *
 */
package com.csf.whoami.common.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mba0051
 *
 */
public class RequestResponseConstants {

    public static final String REQUEST_BODY = "RequestBody";
    public static final String CHANNEL_ID = "channelId";
    public static final String GET_CHANNEL_METHOD = "getChannelId";
    public static final String CHANNEL_FIELD = "channelId";

    /**
     * Constant for YES NO desition.
     *
     * @author tuan
     *
     */
    public enum MarkStatusConstant {

        MARK_NOW("1"), WAITING_LATE("0");

        @Getter
        @Setter
        private String value;

        MarkStatusConstant(String value) {
            this.value = value;
        }

        ;
    }
}
