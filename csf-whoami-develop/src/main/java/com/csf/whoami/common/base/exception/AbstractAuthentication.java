package com.csf.whoami.common.base.exception;

import org.springframework.security.core.Authentication;

public abstract class AbstractAuthentication implements Authentication {

    private static final long serialVersionUID = 1L;

    public static final String ERR_PAGE = "/authErrorPC";

    public static final String KINO_ERR_PAGE = "/kinoAuthError";

    /**
     * �A�N�Z�X���x���`�F�b�N���s���B<p>
     * �����ꂽ�A�N�Z�X���x���łȂ��ꍇ�AAccessLevelException ���X���[����B
     *
     * @param accessLevel �F�؍σ��[�U�[�̃p�[�~�b�V�������x��
     * @param permit      ���s������Ă���p�[�~�b�V�������x��
     * @param param       �����`�F�b�N�ŕK�v�Ȓǉ��p�����[�^�Q
     * @return ���s����������� true�A���s�������Ȃ���� false
     * @throws AccessLevelException ���s�������Ȃ��������A��O�Ƃ��ĕԂ��ꍇ�� throw �����
     */
    public abstract boolean checkAccessLevel(Object accessLevel, Object permit, Object param);

    /**
     * �A�N�Z�X���x���`�F�b�N���s���B<p>
     * �����ꂽ�A�N�Z�X���x���łȂ��ꍇ�AAccessLevelException ���X���[����B
     *
     * @param accessLevel �F�؍σ��[�U�[�̃p�[�~�b�V�������x��
     * @param permit      ���s������Ă���p�[�~�b�V�������x��
     * @return ���s����������� true�A���s�������Ȃ���� false
     * @throws AccessLevelException ���s�������Ȃ��������A��O�Ƃ��ĕԂ��ꍇ�� throw �����
     */
    public abstract boolean checkAccessLevel(Object accessLevel, Object permit);

}
