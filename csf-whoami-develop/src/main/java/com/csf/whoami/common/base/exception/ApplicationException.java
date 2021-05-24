package com.csf.whoami.common.base.exception;

import java.io.Serializable;

/**
 * �A�v���P�[�V�����ėp��O.
 *
 * @author user
 */
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException implements Serializable {

    /**
     * �����ƂȂ��O
     */
    private Throwable rootCause;

    /**
     * Constructor for ApplicationException.
     */
    public ApplicationException() {
        super();
    }

    /**
     * Constructor for ApplicationException.
     */
    public ApplicationException(String s) {
        super(s);
    }

    /**
     * Constructor for ApplicationException.
     */
    public ApplicationException(Throwable rootCause) {
        super();
        setRootCause(rootCause);
    }

    /**
     * Constructor for ApplicationException.
     */
    public ApplicationException(String s, Throwable rootCause) {
        super(s);
        setRootCause(rootCause);
    }

    /**
     * Returns the rootCause.
     *
     * @return Throwable
     */
    public Throwable getRootCause() {
        return rootCause;
    }

    /**
     * Sets the rootCause.
     *
     * @param rootCause The rootCause to set
     */
    public void setRootCause(Throwable rootCause) {
        this.rootCause = rootCause;
    }

    /* �����ƂȂ��O��\�������������߂ɃI�[�o�[���C�h
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder str = new StringBuilder(super.toString());
        str.append(" rootCause=");
        str.append(getRootCause());
        return str.toString();
    }

}
