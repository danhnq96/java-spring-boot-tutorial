/**
 *
 */
package com.csf.whoami.backend.repository;

import java.util.List;

import com.csf.whoami.backend.entity.ScheduleEntity;

/**
 * @author mba0051
 *
 */
public interface ScheduleRepository {

    List<ScheduleEntity> findAll();
//extends JpaRepository<ScheduleEntity, String> {

}
