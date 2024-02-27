package com.khoabeo.quanlyphongkham.Constants;

public class AppConstants {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_SECRET_KEY = "7916b1fe5851b9da0b9458ad404f1104085706c03c228c8dab8482cae97f86c6";
    public static final int JWT_EXPIRATION_TIME = 604800; /// 7 day hết hạn token 604800
    public static final int JWT_REFRESH_TOKEN_EXPIRY = 1000 * 600; //REFRESH_TOKEN_EXPIRY is 10 minues

}
