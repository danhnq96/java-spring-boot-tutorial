/**
 *
 */
package com.csf.whoami.common.domain;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class GroupInfoDomain {

    private String groupId;
    private String groupName;
    private String groupUrl;
    private String groupImg;
    private List<ChannelInfoDomain> channels;
}
