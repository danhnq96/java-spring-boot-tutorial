package com.csf.whoami.converter;

import com.csf.whoami.common.domain.GroupDomain;
import com.csf.whoami.database.TbGroup;
import com.google.api.client.repackaged.com.google.common.base.Strings;

public class ConvertGroupDTO {

    public static GroupDomain dbToDomain(TbGroup entity) {
        if (entity == null) {
            return null;
        }

        GroupDomain domain = new GroupDomain();
        domain.setGroupId(String.valueOf(entity.getId()));
        domain.setGroupName(entity.getGroupName());
        domain.setGroupUrl(entity.getGroupUrl());
        return domain;
    }

    public static TbGroup domainToDb(GroupDomain domain) {
        if (domain == null) {
            return null;
        }
        TbGroup entity = new TbGroup();
        if (domain.getGroupId() == null) {
            entity.setId(null);
        } else {
            entity.setId(Long.parseLong(domain.getGroupId()));
        }
        entity.setGroupName(domain.getGroupName());
        entity.setGroupUrl(domain.getGroupUrl());
        entity.setGroupType(domain.getGroupType());
        entity.setIsPrivate(Strings.isNullOrEmpty(domain.getIsPrivate()) ? 'Y' : domain.getIsPrivate().charAt(0));
        entity.setIsPublish(Strings.isNullOrEmpty(domain.getIsPublish()) ? 'Y' : domain.getIsPublish().charAt(0));
        entity.setIsLock(Strings.isNullOrEmpty(domain.getIsLock()) ? 'N' : domain.getIsLock().charAt(0));
        entity.setIsClosed(Strings.isNullOrEmpty(domain.getIsClosed()) ? 'N' : domain.getIsClosed().charAt(0));
        entity.setGroupDescription(domain.getGroupDescription());
        return entity;
    }
}
