package com.qzero.blog.exception;

public class ErrorCodeList {

    /**
     * Login failed because either username or password is wrong
     */
    public static final int WRONG_LOGIN_INFO=-100;

    /**
     * Action failed because account is not activated
     */
    public static final int ACCOUNT_NOT_ACTIVATED=-101;

    /**
     * An error caused by unknown reasons
     */
    public static final int UNKNOWN_REASON=-102;

    /**
     * For using invalid token
     */
    public static final int INVALID_TOKEN=-103;

}
