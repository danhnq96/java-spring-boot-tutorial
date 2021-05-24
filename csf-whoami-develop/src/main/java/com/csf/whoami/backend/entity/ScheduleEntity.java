/**
 *
 */
package com.csf.whoami.backend.entity;

import com.csf.whoami.database.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mba0051
 *
 */

//@Entity
//@Table(name = "HOS01_SCHEDULE")
//@Where(clause = "delflg = 0")
@Getter
@Setter
public class ScheduleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //	@Column(name = "SCHEDULE_TITLE")
    private String scheduleTitle;
    //	@Column(name = "SCHEDULE_TIME_START")
    private String scheduleTimeStart;
    //	@Column(name = "SCHEDULE_TIME_END")
    private String scheduleTimeEnd;
    //	@Column(name = "SCHEDULE_CONTENT")
    private String scheduleContent;
    //	@Column(name = "STATUS")
    private String status;
    //	@Column(name = "MAP_LOCATION")
    private String mapLocation;
}
