/*
 * -----------------------------------------------------------------------
 * @(#)JobLogUtil.java
 *
 * Copyright 2014 Seino Information Service Co.,Ltd. All rights reserved.
 *
 * -----------------------------------------------------------------------
 * �V�X�e�����@�@�F����
 * �T�u�V�X�e�����F�W���u
 * �v���O�������@�F�W���u���O���[�e�B���e�B
 * -----------------------------------------------------------------------
 * �N���X���FJobLogUtil
 * �쐬�����F2014.05.30 T13-471 H.Kinomura	�V�K�쐬
 * �C�������F9999.99.99 X99-999 Name		�C�����e
 * �C�������F
 * -----------------------------------------------------------------------
 */
package com.csf.whoami.common.base.logging;

public class JobLogUtil {

    public static String getText(String programid, String jobKanriNo, String naiyo) {
        return new StringBuilder("[").append(programid).append("]").append(jobKanriNo).append(":").append(naiyo).toString();
    }

    public static void info(Logger log, String programid, String jobKanriNo, String naiyo) {
        log.info(getText(programid, jobKanriNo, naiyo));
    }

    public static void warn(Logger log, String programid, String jobKanriNo, String naiyo) {
        log.warn(getText(programid, jobKanriNo, naiyo));
    }

    public static void warn(Logger log, String programid, String jobKanriNo, String naiyo, Exception ex) {
        log.warn(getText(programid, jobKanriNo, naiyo), ex);
    }

    public static void fatal(Logger log, String programid, String jobKanriNo, String naiyo) {
        log.fatal(getText(programid, jobKanriNo, naiyo));
    }

    public static void fatal(Logger log, String programid, String jobKanriNo, String naiyo, Exception ex) {
        log.fatal(getText(programid, jobKanriNo, naiyo), ex);
    }
}
