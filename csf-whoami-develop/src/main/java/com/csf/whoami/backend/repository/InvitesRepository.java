/**
 *
 */
package com.csf.whoami.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.backend.entity.InvitesEntity;

/**
 * @author mba0051
 *
 */
public interface InvitesRepository extends JpaRepository<InvitesEntity, String> {

}
