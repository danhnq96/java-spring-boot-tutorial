/**
 *
 */
package com.csf.whoami.common.domain.dating;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tuan
 *
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ApiModel(description = "All details about the Employee. ")
public class SchedulesDomain {

    @ApiModelProperty(notes = "The database generated employee ID")
    private String title;
    @ApiModelProperty(notes = "The employee first name")
    private String timeStart;
    @ApiModelProperty(notes = "The employee last name")
    private String timeEnd;
    @ApiModelProperty(notes = "The employee email id")
    private String content;
    private String status;
    private String mapLocation;

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the timeStart
     */
    public String getTimeStart() {
        return timeStart;
    }

    /**
     * @param timeStart the timeStart to set
     */
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * @return the timeEnd
     */
    public String getTimeEnd() {
        return timeEnd;
    }

    /**
     * @param timeEnd the timeEnd to set
     */
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the mapLocation
     */
    public String getMapLocation() {
        return mapLocation;
    }

    /**
     * @param mapLocation the mapLocation to set
     */
    public void setMapLocation(String mapLocation) {
        this.mapLocation = mapLocation;
    }

    /**
     * @param title
     * @param timeStart
     * @param timeEnd
     * @param content
     * @param status
     * @param mapLocation
     */
    public SchedulesDomain(String title, String timeStart, String timeEnd, String content, String status,
                           String mapLocation) {
        super();
        this.title = title;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.content = content;
        this.status = status;
        this.mapLocation = mapLocation;
    }

    /**
     *
     */
    public SchedulesDomain() {
        super();
        // TODO Auto-generated constructor stub
    }
}
