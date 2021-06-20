package com.jack.huncho.conference;

public class Constants {
    public static final String NOT_FOUND = "Entity not found";
    public static final String NOT_FOUND_STATUS_CODE = "404";

    // HTTP commands Speaker
    public static final String SPEAKER_GET = "/speaker/get";
    public static final String SPEAKER_GET_ONE = "/speaker/get/{id}";
    public static final String SPEAKER_POST = "/speaker/post";
    public static final String SPEAKER_PUT = "/speaker/put/{id}";
    public static final String SPEAKER_DELETE = "/speaker/delete/{id}";

    // HTTP commands Session
    public static final String SESSION_GET = "/session/get";
    public static final String SESSION_GET_ONE = "/session/get/{id}";
    public static final String SESSION_POST = "/session/post";
    public static final String SESSION_PUT = "/session/put/{id}";
    public static final String SESSION_DELETE = "/session/delete/{id}";
}
