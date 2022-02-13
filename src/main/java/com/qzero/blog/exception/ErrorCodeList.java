package com.qzero.blog.exception;

import java.util.HashMap;
import java.util.Map;

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


    public static Map<Integer,String> reasonMap=new HashMap<>();
    static {
        reasonMap.put(WRONG_LOGIN_INFO,"用户名或密码错误导致登陆失败");
        reasonMap.put(ACCOUNT_NOT_ACTIVATED,"账号已被冻结");
        reasonMap.put(UNKNOWN_REASON,"未知原因");
        reasonMap.put(INVALID_TOKEN,"无效的登陆凭据");
    }
}
