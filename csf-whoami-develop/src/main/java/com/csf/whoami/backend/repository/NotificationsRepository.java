/**
 *
 */
package com.csf.whoami.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.backend.entity.NotificationsEntity;

/**
 * @author mba0051
 *
 */
public interface NotificationsRepository extends JpaRepository<NotificationsEntity, String> {

}
