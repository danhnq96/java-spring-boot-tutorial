/**
 *
 */
package com.csf.whoami.backend.service;

import com.csf.whoami.common.domain.dating.SchedulesDomain;

import reactor.core.publisher.Flux;

/**
 * @author mba0051
 *
 */
public interface ScheduleService {

    Flux<SchedulesDomain> findAll();

}
