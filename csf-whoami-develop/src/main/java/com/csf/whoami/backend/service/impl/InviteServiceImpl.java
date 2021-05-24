package com.csf.whoami.backend.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.whoami.backend.entity.InvitesEntity;
import com.csf.whoami.backend.entity.NotificationsEntity;
import com.csf.whoami.backend.entity.Oauth2UserEntity;
import com.csf.whoami.backend.exception.CustomException;
import com.csf.whoami.backend.exception.ErrorException;
import com.csf.whoami.backend.repository.GroupRepository;
import com.csf.whoami.backend.repository.InvitesRepository;
import com.csf.whoami.backend.repository.NotificationsRepository;
import com.csf.whoami.backend.repository.Oauth2UserRepository;
import com.csf.whoami.backend.service.InviteService;
import com.csf.whoami.common.constant.DatabaseConstants;
import com.csf.whoami.common.constant.DateFormatConstant;
import com.csf.whoami.common.domain.InviteGroupDetailDomain;
import com.csf.whoami.common.domain.InviteGroupDomain;
import com.csf.whoami.common.utilities.AuthenticationUtils;
import com.csf.whoami.common.utilities.DateTimeUtils;
import com.csf.whoami.database.TbGroup;

@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    private Oauth2UserRepository oauth2UserRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private NotificationsRepository notificationsRepository;
    @Autowired
    private InvitesRepository invitesRepository;

    /**
     * Invite user to Group.
     */
    @Transactional
    @Override
    public String inviteToGroup(InviteGroupDomain domain) {

        validateInviteGroup(domain);

        String inviteId = inviteUserToGroup(domain.getUserId(),
                domain.getGroupId(),
                domain.getJoinType(),
                "");
//				StringUtils.convertDateToString(new Date()));

        notificationsForUser(inviteId);
        return inviteId;
    }

    /**
     * Notifications invite to all user.
     *
     * @param inviteId
     * @return
     * @author mba0051
     */
    private void notificationsForUser(String inviteId) {

        StringBuilder inviteLink = new StringBuilder();
        inviteLink.append("invite/groups/");
        inviteLink.append(inviteId);

        NotificationsEntity notify = new NotificationsEntity();
        notify.setNotifyLink(inviteLink.toString());
        notify.setNotifyStatus(DatabaseConstants.NotifyStatusConstant.NEW.getValue());
        notify.setNotifyType(DatabaseConstants.NotificationsTypeConstant.GROUP.getValue());
        notify = notificationsRepository.save(notify);
        if (notify == null) {
            throw new CustomException(ErrorException.CANT_NOTIFICATION.getMessage(),
                    ErrorException.CANT_NOTIFICATION.getCode(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Invite user to group.
     *
     * @param beInvitedId
     * @param joinType
     * @param expireDate
     * @return
     * @author mba0051
     */
    private String inviteUserToGroup(String beInvitedId, String detailId, String joinType, String expireDate) {

        InvitesEntity invite = new InvitesEntity();
        invite.setOwnerId(AuthenticationUtils.getCurrentUserId());
        invite.setInvitedId(beInvitedId);
        invite.setDetailId(detailId);
        invite.setExpireDate(DateTimeUtils.convertStringToDateOrNull(expireDate, DateFormatConstant.yyyyMMdd));
        invite.setMessage("Welcome to Group");
        invite.setCondition(joinType); // Add direct to group.
        invite = invitesRepository.save(invite);
        if (invite == null) {
            throw new CustomException(ErrorException.CANT_INVITE.getMessage(),
                    ErrorException.CANT_INVITE.getCode(),
                    HttpStatus.BAD_REQUEST);
        }
        return String.valueOf(invite.getId());
    }

    /**
     * Validate data and format.
     *
     * @param domain
     */
    private void validateInviteGroup(InviteGroupDomain domain) {

        commonValidate(domain);
        TbGroup group = groupRepository.findById(Long.parseLong(domain.getGroupId())).orElse(null);
        if (group == null) {
            throw new CustomException(ErrorException.INVALID_GROUP.getMessage(), ErrorException.INVALID_GROUP.getCode(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Common validate domain.
     *
     * @param domain
     * @author mba0051
     */
    private void commonValidate(InviteGroupDomain domain) {
//		String currentUserId = AuthenticationUtils.getCurrentUserId();
//		if (!StringUtils.isValidString(currentUserId)) {
//			throw new CustomException(ErrorException.INVALID_USER.getMessage(), ErrorException.INVALID_USER.getCode(),
//					HttpStatus.BAD_REQUEST);
//		}

        // Validate format.
        if (!DatabaseConstants.GroupRequireTypeConstant.Direct.getValue().equalsIgnoreCase(domain.getJoinType())
                || !DatabaseConstants.GroupRequireTypeConstant.RequireAuthen.getValue().equalsIgnoreCase(domain.getJoinType())
                || !DatabaseConstants.GroupRequireTypeConstant.RequireExp.getValue().equalsIgnoreCase(domain.getJoinType())
                || !DatabaseConstants.GroupRequireTypeConstant.WaitAccept.getValue().equalsIgnoreCase(domain.getJoinType())) {
            throw new CustomException(ErrorException.INVALID_FORMAT.getMessage(),
                    ErrorException.INVALID_FORMAT.getCode(), HttpStatus.BAD_REQUEST);
        }

        // Check exist user
        Oauth2UserEntity user = oauth2UserRepository.findById(domain.getUserId()).orElse(null);
        if (user == null) {
            throw new CustomException(ErrorException.INVALID_USER.getMessage(), ErrorException.INVALID_USER.getCode(),
                    HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Get invite detail.
     *
     * @author mba0051
     */
    @Override
    public InviteGroupDetailDomain getInviteGroupDetail(String inviteId) {

        InviteGroupDetailDomain inviteDomain = new InviteGroupDetailDomain();
        InvitesEntity invite = invitesRepository.findById(inviteId).orElse(null);
        if (invite == null) {
            throw new CustomException(ErrorException.NOT_EXIST_INVITE.getMessage(),
                    ErrorException.NOT_EXIST_INVITE.getCode(), HttpStatus.BAD_REQUEST);
        }
        if (DateTimeUtils.compareDateTime(invite.getExpireDate(), new Date()) == -1) {
            throw new CustomException(ErrorException.INVITE_EXPIRED.getMessage(),
                    ErrorException.INVITE_EXPIRED.getCode(), HttpStatus.BAD_REQUEST);
        }

        TbGroup group = groupRepository.findById(Long.parseLong(invite.getDetailId())).orElse(null);
        if (group == null) {
            throw new CustomException(ErrorException.INVALID_GROUP.getMessage(),
                    ErrorException.INVALID_GROUP.getCode(),
                    HttpStatus.BAD_REQUEST);
        }

        inviteDomain.setInviteId(String.valueOf(invite.getId()));
        inviteDomain.setGroupId(String.valueOf(group.getId()));
        inviteDomain.setGroupName(group.getGroupName());
//		inviteDomain.setExprireDate(StringUtils.convertDateToString(invite.getExpireDate()));

        return inviteDomain;
    }

    @Override
    public String inviteToChannel(String groupId, InviteGroupDomain domain) {
        validateInviteChannel(groupId, domain);

        String inviteId = inviteUserToGroup(domain.getUserId(),
                domain.getGroupId(),
                domain.getJoinType(),
                "");
//				StringUtils.convertDateToString(new Date()));

        notificationsForUser(inviteId);
        return inviteId;

    }

    private void validateInviteChannel(String groupId, InviteGroupDomain domain) {

//		TbGroup groupChannel = groupRepository.findByIdAndParentGroup(domain.getGroupId(), groupId);
//		if (groupChannel == null) {
//			throw new CustomException(ErrorException.INVALID_GROUP.getMessage(), ErrorException.INVALID_GROUP.getCode(),
//					HttpStatus.BAD_REQUEST);
//		}
    }
}
