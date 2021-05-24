/**
 *
 */
package com.csf.whoami.backend.service;

import com.csf.whoami.common.domain.InviteGroupDetailDomain;
import com.csf.whoami.common.domain.InviteGroupDomain;

/**
 * @author mba0051
 *
 */
public interface InviteService {

    String inviteToGroup(InviteGroupDomain domain);

    String inviteToChannel(String groupId, InviteGroupDomain domain);

    InviteGroupDetailDomain getInviteGroupDetail(String inviteId);
}
