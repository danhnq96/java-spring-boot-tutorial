package com.csf.whoami.common.domain;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
@NoArgsConstructor
public class GroupDomain {

    @JsonInclude(Include.NON_NULL)
    private String groupId;
    @Length(min = 3, max = 64)
    private String groupName;
    @Length(min = 3, max = 64)
    private String groupUrl;
    @JsonInclude(Include.NON_NULL)
    @Length(min = 3, max = 20)
    private String groupType;
    private String isPrivate;
    private String isPublish;
    @JsonInclude(Include.NON_NULL)
    private String isLock;
    @JsonInclude(Include.NON_NULL)
    private String isClosed;
    private String groupDescription;
}
