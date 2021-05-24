package com.csf.whoami.util;

import java.util.Locale;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import com.csf.whoami.constant.UrlConstants;

import lombok.Getter;
import lombok.Setter;

public final class MessageUtil {

    @Getter
    @Setter
    MessageResources resource;
    private static MessageUtil _inst = null;

    private MessageUtil(MessageResources res) {
        super();
        resource = res;
    }

    public static MessageUtil getInstance(MessageResources res) {
        if (_inst == null) {
            _inst = new MessageUtil(res);
        }
        return _inst;
    }

    public static MessageUtil getInstance() {
        if (_inst == null) {
            _inst = new MessageUtil(new MyMessageResources());
        }
        return _inst;
    }

    public static String getMessage(String key) {
        String message = MessageUtil.getInstance().resource.getMessage(key);
        return message;
    }

    public static String getMessage(String key, String... args) {
        return MessageUtil.getInstance().resource.getMessage(key, args);
    }

    public static String getMessage(Locale local, String key) {
        return MessageUtil.getInstance().resource.getMessage(local, key);
    }

    public static String getMessage(Locale local, String key, String... args) {
        return MessageUtil.getInstance().resource.getMessage(local, key, args);
    }

    public static class MyMessageResources implements MessageResources {

        public MyMessageResources() {
        }

        @Override
        public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
            return null;
        }

        public String getMessage(Locale locale, String key) {
            locale = Locale.ROOT;
//            if (locale == null) {
//                locale = Locale.getDefault();
//            }

//            try {
//                if (!mutiBundleMap.containsKey(locale.toString())) {
//                    mutiBundleMap.put(locale.toString(), new MultipleResourceBundle(locale, "i18n/message"));
//                }
//                return mutiBundleMap.get(locale.toString()).getString(key);
//            } catch (MissingResourceException e) {
//                return null;
//            }
            return new MultipleResourceBundle(locale, UrlConstants.MESSAGE_PATH).getString(key);
        }

        @Override
        public String getMessage(String key, Object[] args, Locale locale) throws NoSuchMessageException {
            locale = Locale.ROOT;
//            if (locale == null) {
//                locale = Locale.getDefault();
//            }

            String message = new MultipleResourceBundle(locale, UrlConstants.MESSAGE_PATH).getString(key);
            return message;
        }

        @Override
        public String getMessage(Locale locale, String key, String... args) {
            return getMessage(key, args, locale);
        }

        @Override
        public String getMessage(String key) {
            String message = getMessage(key, null, Locale.getDefault());
            return message;
        }

        @Override
        public String getMessage(String key, String... args) {
            return getMessage(key, args, Locale.getDefault());
        }

        @Override
        public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
            return null;
        }
    }
}