package com.csf.whoami.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.csf.whoami.common.domain.GroupDomain;
import com.csf.whoami.common.domain.GroupInfoDomain;
import com.csf.whoami.dto.SearchVO;

public interface GroupService {

    List<GroupDomain> findAllByUser(String userId);

    List<GroupDomain> getGroupsByGroupName(String groupName, String groupType,
                                           boolean isAuthenticated, String userId);

    GroupDomain addNewGroup(GroupDomain domain, String userId) throws Exception;

    List<GroupDomain> getGroupsByUsername(String username);

    GroupDomain updateGroupInformation(GroupDomain domain, String userId) throws Exception;

    void addMemberToGroup(String groupId, String userId, String userId2) throws Exception;

    GroupInfoDomain getChannelByGroup(String groupId, String userId) throws Exception;

    GroupDomain addTempGroup(GroupDomain domain) throws Exception;

    boolean checkUserInChannel(String channelId, String userId);

    Page<GroupDomain> groupList(SearchVO search, Pageable pageable);

    GroupDomain groupDetail(Long id);

    Long registerGroup(GroupDomain groupDetail);
}
