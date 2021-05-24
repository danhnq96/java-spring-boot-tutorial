package com.csf.whoami.constant;

public class DefaultConstant {
    public static final long PRODUCT_SINGLE = 1L;
    public static final long PRODUCT_SET = 2L;
    public static final long PRODUCT_CURATION = 3L;
    public static final String MSG_FAIL = "FAIL";                // 기본값
    public static final String MSG_NONE = "NONE";                // 기본값
    public static final String MSG_SUCCESS = "SUCCESS";        // 성공
    public static final String MSG_UPLOAD_FAIL = "UPLOAD_FAIL"; // 실패
    public static final String DDALGICONG_URL = "https://m-dev.ddalgicong.com";

    /**
     * 파일 업로드
     **/
    public static final String WJTHINKBIG_DOWN_PATH = "http://down.wjthinkbig.com"; // wj thinkbig down 경로 -> 이미지 보는 패스
    public static final String WJTHINKBIG_CACHE_PATH = "http://cache.wjthinkbig.com"; // wj thinkbig cache 경로

    public static final String SECURE_UPLOAD_PATH =
            "http://cdn.openapi.wjthinkbig.com/auth/OpenApi.php?a=up&file=%s&ttl=600&userID=wjth@ddalgicong"; // secure url 경로 : %s 부분 -> 파일패스를 base64
    public static final String DO_PURGE_PATH =
            "http://cdn.openapi.wjthinkbig.com/auth/OpenApi.php?a=purge&file=%s&ttl=0&service=sdd&userID=wjth@ddalgicong"; // purge url 경로
    public static final String SECURE_DELETE_PATH =
            "http://cdn.openapi.wjthinkbig.com/auth/OpenApi.php?a=del&file=%s&ttl=600&userID=wjth@ddalgicong"; // secure delete url 경로
    public static final String SECURE_CONTENT_PATH =
            "http://cdn.openapi.wjthinkbig.com/auth/OpenApi.Stream.php?userID=wjth@ddalgicong&domain=secureurl.wjthinkbig.com&pubName=secureurl&prefix=mp4&ttl=600000&file=%s&a=streaming&playType=iphone";
}
