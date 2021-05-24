/**
 *
 */
package com.csf.whoami.backend.adapter;

import com.csf.whoami.common.domain.GroupDomain;
import com.csf.whoami.database.TbGroup;

/**
 * @author tuan
 *
 */
public class GroupDomainAdapter {

    public static GroupDomain convertEntityToDomain(TbGroup entity) {
        GroupDomain domain = new GroupDomain();
        domain.setGroupId(String.valueOf(entity.getId()));
        domain.setGroupName(entity.getGroupName());
        domain.setGroupType(entity.getGroupType());
        return domain;
    }

    public static TbGroup convertDomainToEntity(GroupDomain domain) {
        TbGroup entity = initialEntity();
//		if (StringUtils.isValidString(domain.getGroupId())) {
//			entity.setId(domain.getGroupId());
//		} else {
//			entity.setId(StringUtils.generateUUID());
//		}
        entity.setGroupName(domain.getGroupName());
        entity.setGroupType(domain.getGroupType());
        return entity;
    }

    private static TbGroup initialEntity() {
        TbGroup entity = new TbGroup();
        return entity;
    }
}
