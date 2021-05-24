/**
 *
 */
package com.csf.whoami.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.backend.entity.UserGroupEntity;

/**
 * @author tuan
 *
 */
public interface UserGroupRepository extends JpaRepository<UserGroupEntity, String> {

    UserGroupEntity findAllByUserIdAndGroupId(String userId, String groupId);

}
