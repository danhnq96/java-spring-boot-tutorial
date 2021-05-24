/**
 *
 */
package com.csf.whoami.backend.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.whoami.backend.repository.ScheduleRepository;
import com.csf.whoami.backend.service.ScheduleService;
import com.csf.whoami.common.domain.dating.SchedulesDomain;

import reactor.core.publisher.Flux;

/**
 * @author mba0051
 *
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepo;

    @Override
    public Flux<SchedulesDomain> findAll() {
        return Flux.fromIterable(scheduleRepo.findAll().stream()
                .map(schedule -> new SchedulesDomain(schedule.getScheduleTitle(), schedule.getScheduleTimeStart(),
                        schedule.getScheduleTimeEnd(), schedule.getScheduleContent(), schedule.getStatus(),
                        schedule.getMapLocation()))
                .collect(Collectors.toList()));
    }

}
