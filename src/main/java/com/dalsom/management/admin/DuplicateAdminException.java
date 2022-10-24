package com.dalsom.management.admin;

import com.dalsom.management.common.DuplicateMemberException;

public class DuplicateAdminException extends DuplicateMemberException {

    public DuplicateAdminException() {
        super();
    }

    public DuplicateAdminException(String message) {
        super(message);
    }

    public DuplicateAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateAdminException(Throwable cause) {
        super(cause);
    }

    public DuplicateAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
