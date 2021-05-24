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
public class InviteGroupDomain {

    private String userId;
    private String groupId;
    private String joinType; // GroupRequireTypeConstant

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

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
     * @return the joinType
     */
    public String getJoinType() {
        return joinType;
    }

    /**
     * @param joinType the joinType to set
     */
    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }
}
