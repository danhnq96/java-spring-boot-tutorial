package com.csf.whoami.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

public interface MessageResources extends MessageSource {

    String getMessage(Locale locale, String key);

    String getMessage(Locale locale, String key, String... args);

    String getMessage(String key);

    String getMessage(String key, String... args);
}
