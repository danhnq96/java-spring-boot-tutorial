/**
 *
 */
package com.csf.whoami.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.whoami.backend.entity.UserInfo;
import com.csf.whoami.backend.service.GroupService;
import com.csf.whoami.backend.service.NotificationService;
import com.csf.whoami.common.domain.ChannelQuizDomain;

/**
 * @author tuan
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private GroupService groupService;

    @Override
    public List<ChannelQuizDomain> getQuizByChannel(String channelId, UserInfo userdto) throws Exception {
        if (!groupService.checkUserInChannel(channelId, userdto.getUserId())) {
            throw new Exception("User not in channel.");
        }
        return null;
    }

}
