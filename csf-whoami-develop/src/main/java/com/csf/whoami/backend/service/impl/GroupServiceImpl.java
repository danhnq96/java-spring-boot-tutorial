package com.csf.whoami.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.whoami.backend.adapter.GroupDomainAdapter;
import com.csf.whoami.backend.entity.Oauth2UserEntity;
import com.csf.whoami.backend.entity.UserGroupEntity;
import com.csf.whoami.backend.exception.CustomException;
import com.csf.whoami.backend.exception.ErrorException;
import com.csf.whoami.backend.repository.GroupRepository;
import com.csf.whoami.backend.repository.Oauth2UserRepository;
import com.csf.whoami.backend.repository.UserGroupRepository;
import com.csf.whoami.backend.service.GroupService;
import com.csf.whoami.common.domain.GroupDomain;
import com.csf.whoami.common.domain.GroupInfoDomain;
import com.csf.whoami.converter.ConvertGroupDTO;
import com.csf.whoami.database.TbGroup;
import com.csf.whoami.dto.SearchVO;
import com.csf.whoami.util.ObjectUtil;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private Oauth2UserRepository userRepository;

    @Override
    public List<GroupDomain> findAllByUser(String userId) {
        List<TbGroup> entities = groupRepository.findAllByUser(userId);
        List<GroupDomain> groups = new ArrayList<GroupDomain>();
        for (TbGroup item : entities) {
            groups.add(GroupDomainAdapter.convertEntityToDomain(item));
        }
        return groups;
    }

    @Override
    public List<GroupDomain> getGroupsByGroupName(String groupName, String groupType, boolean isAuthenticated,
                                                  String userId) {
        List<GroupDomain> groupReturn = new ArrayList<GroupDomain>();
        TbGroup group = groupRepository.findByGroupNameAndGroupType(groupName, groupType);
        if (group == null) {
            if (!isAuthenticated) {
                // Anonimous.
                return groupReturn;
            } else {
                // TODO: advance search.
            }

        } else {
            if (!isAuthenticated) {
                // Anonimous.
                groupReturn.add(GroupDomainAdapter.convertEntityToDomain(group));
            } else {
                TbGroup isExist = groupRepository.findMyGroup(groupName, groupType, userId);
                if (isExist == null) {
                    groupReturn.add(GroupDomainAdapter.convertEntityToDomain(isExist));
                }
            }
        }

        return groupReturn;
    }

    @Override
    public GroupDomain addNewGroup(GroupDomain domain, String userId) throws Exception {
        TbGroup entity = GroupDomainAdapter.convertDomainToEntity(domain);
        entity.setGroupOwner(userId);
        entity = groupRepository.save(entity);
        if (entity == null) {
            throw new CustomException(ErrorException.CANT_CREATE_GROUP.getMessage(),
                    ErrorException.CANT_CREATE_GROUP.getCode(), HttpStatus.BAD_REQUEST);
        }
        return GroupDomainAdapter.convertEntityToDomain(entity);
    }

    @Override
    public List<GroupDomain> getGroupsByUsername(String username) {
        List<TbGroup> entities = groupRepository.findAllByUsername(username);
        List<GroupDomain> groups = new ArrayList<GroupDomain>();
        for (TbGroup item : entities) {
            groups.add(GroupDomainAdapter.convertEntityToDomain(item));
        }
        return groups;
    }

    @Transactional
    @Override
    public GroupDomain updateGroupInformation(GroupDomain domain, String userId) throws Exception {
        TbGroup group = groupRepository.findById(Long.parseLong(domain.getGroupId())).orElse(null);
        if (group == null) {
            throw new Exception("Can not found group to update.");
        }

        if (userId.equals(group.getGroupOwner())) {
            group.setGroupName(domain.getGroupName());
            group.setGroupType(domain.getGroupType());
            group = groupRepository.save(group);

            if (group == null) {
                throw new Exception("Can not update group.");
            }
        } else {
            throw new Exception("Have no permission update this group.");
        }

        return GroupDomainAdapter.convertEntityToDomain(group);
    }

    @Transactional
    @Override
    public void addMemberToGroup(String groupId, String userAdded, String ownerUser) throws Exception {
        TbGroup entity = groupRepository.findById(Long.parseLong(groupId)).orElse(null);
        if (entity == null) {
            throw new Exception("Can not found group to update.");
        }

        if (!ownerUser.equals(entity.getGroupOwner())) {
            throw new Exception("Have no permission add member to this group.");
        }

        // Get user member
        Oauth2UserEntity user = userRepository.findById(userAdded).orElse(null);
        if (user == null) {
            throw new Exception("Can not found User.");
        }

        // Add member to group.
        UserGroupEntity userGroup = new UserGroupEntity();
        userGroup.setGroup(entity);
        userGroup.setUser(user);

        userGroupRepository.save(userGroup);
    }

    /**
     * Get group and channel information.
     *
     * @author tuan
     */
    @Transactional
    @Override
    public GroupInfoDomain getChannelByGroup(String groupId, String userId) throws Exception {
        UserGroupEntity checkUserGroup = userGroupRepository.findAllByUserIdAndGroupId(userId, groupId);
        if (checkUserGroup == null) {
            TbGroup group = groupRepository.findById(Long.parseLong(groupId)).orElse(null);
            if (group == null) {
                throw new Exception("Group is not exist.");
            }
            Oauth2UserEntity user = userRepository.findById(userId).orElse(null);

            checkUserGroup = new UserGroupEntity();
            checkUserGroup.setGroup(group);
            checkUserGroup.setUser(user);
            checkUserGroup = userGroupRepository.save(checkUserGroup);

            if (checkUserGroup == null) {
                throw new Exception("Can not create group.");
            }
        }

        GroupInfoDomain groupInfo = new GroupInfoDomain();
        groupInfo.setGroupId(String.valueOf(checkUserGroup.getGroup().getId()));
        groupInfo.setGroupName(checkUserGroup.getGroup().getGroupName());
        groupInfo.setGroupUrl(checkUserGroup.getGroup().getGroupUrl());
        groupInfo.setGroupImg("");

//		groupInfo.setChannels(groupRepository.findAllByParentGroup(groupId).stream()
//				.map(group -> new ChannelInfoDomain(
//						group.getId(),
//						group.getGroupName(),
//						group.getGroupDescription(),
//						"",
//						"")
////						StringUtils.convertDateToString(group.getCreatedDate()),
////						StringUtils.convertObjectToString(group.getIsLock()))
//				).collect(Collectors.toList()));
        return groupInfo;
    }

    /**
     * Register channel after login.
     *
     * @throws Exception
     */
    @Override
    public GroupDomain addTempGroup(GroupDomain domain) throws Exception {
        TbGroup isExist = groupRepository.findByGroupUrlAndGroupType(domain.getGroupUrl(), domain.getGroupType());
        if (isExist != null) {
            throw new Exception("Exist group");
        }
        isExist = GroupDomainAdapter.convertDomainToEntity(domain);
        isExist.setIsLock('0');
        isExist = groupRepository.save(isExist);

        if (isExist == null) {
            throw new Exception("Can not create temp group.");
        }

        return GroupDomainAdapter.convertEntityToDomain(isExist);
    }

    @Override
    public boolean checkUserInChannel(String channelId, String userId) {
        return userGroupRepository.findAllByUserIdAndGroupId(userId, channelId) != null;
    }

    /**
     * Search groups by conditions.
     *
     * @param search
     * @param pageable
     * @return
     */
    @Override
    public Page<GroupDomain> groupList(SearchVO search, Pageable pageable) {
        ObjectUtil.removeEmptyField(search);
        return groupRepository.groupList(search, pageable).map((object -> ConvertGroupDTO.dbToDomain(object)));
    }

    /**
     * Get group information.
     *
     * @param id
     * @return
     */
    @Override
    public GroupDomain groupDetail(Long id) {
        TbGroup group = groupRepository.findById(id).orElse(null);
        return ConvertGroupDTO.dbToDomain(group);
    }

    /**
     * Insert or Update group base entity information.
     *
     * @param groupDetail
     * @return
     */
    @Override
    public Long registerGroup(GroupDomain groupDetail) {
        TbGroup group = ConvertGroupDTO.domainToDb(groupDetail);
        TbGroup result = groupRepository.save(group);
        return result.getId();
    }
}
