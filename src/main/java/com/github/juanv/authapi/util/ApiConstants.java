package com.github.juanv.authapi.util;

public final class ApiConstants {

    private ApiConstants() {}   
    
    
    public static final String DUMMYJSON_BASE_URL   = "https://dummyjson.com";

    
    public static final String PATH_AUTH_LOGIN      = "/auth/login";
    public static final String PATH_AUTH_ME         = "/auth/me";
    public static final String PATH_USERS           = "/users";
    public static final String PATH_LOGIN           = "/login";

    
    public static final String HEADER_COOKIE        = "Cookie";
    public static final String COOKIE_ACCESS_TOKEN  = "accessToken";
    public static final String COOKIE_REFRESH_TOKEN = "refreshToken";

    
    public static final String API_AUTH_BASE  = "/api/auth";
    public static final String API_USERS_BASE = "/api/users";
    
    public static final String TABLE_NAME           = "login_log";

    public static final String COL_ID               = "id";
    public static final String COL_USERNAME         = "username";
    public static final String COL_LOGIN_TIME       = "login_time";
    public static final String COL_ACCESS_TOKEN     = "access_token";
    public static final String COL_REFRESH_TOKEN    = "refresh_token";

    
    public static final int DEFAULT_SKIP  = 0;
    public static final int DEFAULT_LIMIT = 30;
    
}
