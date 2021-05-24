/**
 *
 */
package com.csf.whoami.common.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author mba0051
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RequestChannelDetailDomain {

    private String groupId;
    private String channelId;

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * @param channelId the channelId to set
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
