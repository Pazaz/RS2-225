package com.itspazaz.lostcity.network;

public class LoginProt {

    // Opcodes:

    public static final int CONNECTING = 16;
    public static final int RECONNECTING = 18;

    // Responses:

    public static final int RETRY = 1;

    // ----

    public static final int SUCCESS = 2;

    public static final int SUCCESS_STAFF = 18;

    public static final int SUCCESS_RECONNECT = 15;

    // ----

    public static final int ERR_INVALID_USERNAME = 3;

    public static final int ERR_ACCOUNT_DISABLED = 4;

    public static final int ERR_LOGGED_IN = 5;

    public static final int ERR_OUT_OF_DATE = 6;

    public static final int ERR_WORLD_FULL = 7;

    public static final int ERR_LOGIN_OFFLINE = 8;

    public static final int ERR_LOGIN_LIMIT = 9;

    public static final int ERR_BAD_SESSION = 10;

    public static final int ERR_REJECTED_SESSION = 11;

    public static final int ERR_MEMBERS_ONLY = 12;

    public static final int ERR_UNKNOWN = 13;

    public static final int ERR_UPDATING = 14;

    public static final int ERR_LOGIN_ATTEMPTS = 16;

    public static final int ERR_MEMBERS_AREA = 17;

}
