/**
 *
 */
package com.csf.whoami.common.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tuan
 *
 */
@ApiModel(description = "Thông tin về kênh.")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class ChannelInfoDomain {

    @ApiModelProperty(name = "id", required = true, position = 0, example = "7aaee0e2-6884-4fd7-ba63-21d76723dce2")
    private String channelId;
    @ApiModelProperty(name = "name", required = true, position = 1, example = "Tieng-Anh")
    private String channelName;
    @ApiModelProperty(name = "description", required = true, position = 2, example = "Kênh chuyên về học tiếng Anh.")
    private String channelDescription;
    private String createDate;
    @ApiModelProperty(name = "lock", required = true, position = 3, example = "Tình trạng khoá kênh.")
    private String lockStatus;

    /**
     * @param channelId
     * @param channelName
     * @param description
     * @param createDate
     * @param lockStatus
     */
    public ChannelInfoDomain(Long channelId, String channelName, String description, String createDate,
                             String lockStatus) {
        super();
        this.channelId = String.valueOf(channelId);
        this.channelName = channelName;
        this.channelDescription = description;
        this.createDate = createDate;
        this.lockStatus = lockStatus;
    }
}
