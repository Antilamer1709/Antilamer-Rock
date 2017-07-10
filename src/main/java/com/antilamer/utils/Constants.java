package com.antilamer.utils;

public class Constants {
    public static final String[] RESOURCES_PERMISSION_ALL = {"/", "/index.html", "/user/login", "/user/currentUser",
            "/user/registerUser", "/user/restorePassword", "/getBand", "/bower_components/**", "/CSS/**", "/JS/**",
            "/images/**", "/HTML/home.html", "/HTML/band.html"
    };

    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String MAIL_RESTORE_PASS_SUBCJECT = "Antilamer.tk, restoring password";
    public static final String MAIL_RESTORE_PASS_TEXT = "Hello! Your new password is: ";
}
