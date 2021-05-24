/**
 *
 */
package com.csf.whoami.backend.service;

import java.util.List;

import com.csf.whoami.backend.entity.UserInfo;
import com.csf.whoami.common.domain.ChannelQuizDomain;

/**
 * @author tuan
 *
 */
public interface NotificationService {

    List<ChannelQuizDomain> getQuizByChannel(String channelId, UserInfo userdto) throws Exception;
}
