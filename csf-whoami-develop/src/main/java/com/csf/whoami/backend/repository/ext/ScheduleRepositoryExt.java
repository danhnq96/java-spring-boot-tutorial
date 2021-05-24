/**
 *
 */
package com.csf.whoami.backend.repository.ext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.csf.whoami.backend.entity.ScheduleEntity;
import com.csf.whoami.backend.repository.ScheduleRepository;
import com.csf.whoami.common.utilities.DateTimeUtils;

/**
 * @author mba0051
 *
 */
@Repository
public class ScheduleRepositoryExt implements ScheduleRepository {

    /**
     * Dummy data schedule.
     */
    @Override
    public List<ScheduleEntity> findAll() {

        List<ScheduleEntity> schedules = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        Date timeStart = DateTimeUtils.convertStringToDateOrNull("2019-10-25 10:45:00", DateTimeUtils.YYYYMMDDhhmmss);
        calendar.setTime(timeStart);
//		Date currentDate = null;
        for (int j = 0; j < 7; j++) {
            calendar.add(Calendar.DATE, j);
            for (int i = 0; i < 5; i++) {
                ScheduleEntity schedule = new ScheduleEntity();
                calendar.add(Calendar.HOUR, i * 2);
//				currentDate = calendar.getTime();
//				schedule.setId(StringUtils.generateUUID());
                schedule.setScheduleTitle("Title for schedule " + (j * 7 + i));
//				schedule.setScheduleTimeStart(
//						StringUtils.convertDateToStringFormatPattern(currentDate, DateTimeUtils.YYYYMMDDhhmmss));
                schedule.setScheduleContent("Content for schedule " + (j * 7 + i));
                if (i % 3 == 0 || (j * 7 + i) % 4 == 0) {
                    schedule.setStatus("Confirm");
                } else if (i % 4 == 0 || (j * 7 + i) % 5 == 0) {
                    schedule.setStatus("Reject");
                } else {
                    schedule.setStatus("New");
                }
                schedule.setMapLocation(null);
                schedules.add(schedule);
            }
        }
        return schedules;
    }

}
