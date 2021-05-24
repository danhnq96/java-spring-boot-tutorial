/**
 * Functions list:
 * - Get all notification.
 * - View notification.
 * - Delete notifications.
 * - Get unread notifications.
 */
package com.csf.whoami.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csf.whoami.backend.entity.UserInfo;
import com.csf.whoami.backend.service.NotificationService;
import com.csf.whoami.common.domain.ChannelQuizDomain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/notify")
@Api
public class NotificationsController {

    @Autowired
    private NotificationService notificationService;

    @ApiOperation(value = "Phương thức lấy danh sách các bài quiz trong kênh.")
    @GetMapping("/quiz/{channelId}")
    @Secured({"ROLE_USER"})
    public List<ChannelQuizDomain> getChannelQuiz(@PathVariable("channelId") String channelId,
                                                  Authentication auth) throws Exception {
        UserInfo userdto = (UserInfo) auth.getPrincipal();

        List<ChannelQuizDomain> quizInChannels = notificationService.getQuizByChannel(channelId, userdto);
        return quizInChannels;
    }
}
