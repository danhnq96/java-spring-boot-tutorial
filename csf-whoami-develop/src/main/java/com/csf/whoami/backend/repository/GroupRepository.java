package com.csf.whoami.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csf.whoami.database.TbGroup;
import com.csf.whoami.dto.SearchVO;

public interface GroupRepository extends JpaRepository<TbGroup, Long> {

    // @Query("select group from GroupEntity group inner join user.userRoles
    // userRole inner join userRole.role role where role.code = ?2 and user.id = ?1
    // and user.isDeleted = 0")
//	@Query(value = "select * from H04DT_GROUPS group "
//			+ "inner join H05DT_USER_GROUP userGroup on group.GROUP_ID = userGroup.GROUP_ID "
//			+ "where s.user_id =?1 and s.is_deleted = 0 ", nativeQuery = true)
    TbGroup findByGroupNameAndGroupType(String groupName, String groupType);

    @Query(value = "select group.* from H04DT_GROUPS group "
            + "inner join H05DT_USER_GROUP userGroup on group.GROUP_ID = userGroup.GROUP_ID "
            + "inner join H01DT_USERS users on users.USER_ID = userGroup.USER_ID "
            + "where users.user_id =:userId and group.GROUP_TYPE = :groupType and group.GROUP_NAME = :groupName ", nativeQuery = true)
    TbGroup findMyGroup(@Param("groupName") String groupName, @Param("groupType") String groupType,
                        @Param("userId") String userId);

    @Query(value = "select groups.* from h04dt_groups groups \n"
            + "inner join h05dt_user_group userGroup on groups.GROUP_ID = userGroup.GROUP_ID \n"
            + "inner join h01dt_users users on users.USER_ID = userGroup.USER_ID "
            + "where users.user_id =:userId", nativeQuery = true)
    List<TbGroup> findAllByUser(@Param("userId") String userId);

    @Query(value = "select groups.* from h04dt_groups groups \n"
            + "inner join h05dt_user_group userGroup on groups.GROUP_ID = userGroup.GROUP_ID \n"
            + "inner join h01dt_users users on users.USER_ID = userGroup.USER_ID "
            + "where users.userName =:username", nativeQuery = true)
    List<TbGroup> findAllByUsername(@Param("username") String username);

    //	List<TbGroup> findAllByParentGroup(String groupId);
    TbGroup findByGroupUrlAndGroupType(String groupName, String groupType);

//	TbGroup findByIdAndParentGroup(String groupId, String parentGroup);

    @Query(value = "SELECT m" +
            " FROM TbGroup m" +
            " WHERE ((:#{#search.keyword} IS NULL OR m.groupName LIKE %:#{#search.keyword}%)" +
            " OR (:#{#search.keyword} IS NULL OR m.groupUrl LIKE %:#{#search.keyword}%)" +
            " OR (:#{#search.keyword} IS NULL OR m.groupType LIKE %:#{#search.keyword}%))" +
            " ORDER BY m.groupName ASC")
    Page<TbGroup> groupList(@Param("search") SearchVO search, Pageable pageable);

//	@Query(value = "SELECT new com.csf.whoami.common.domain.GroupDomain(m.id, m.groupName, m.groupUrl, m.groupType)"
//			+ " FROM TbGroup m "
//			+ " WHERE m.id=:id"
//    )
//	GroupDomain groupDetail(@Param("id") Long id);
}
