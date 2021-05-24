package com.csf.whoami.common.base.config;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.csf.whoami.common.base.exception.ApplicationException;

@Component
public class ApplicationProfile {

    private static Environment environment;

    public ApplicationProfile(Environment environment) {
        ApplicationProfile.environment = environment;
    }

    public static Environment getEnvironment() {
        if (environment == null) {
            throw new ApplicationException("Environment has not been set yet");
        }
        return environment;
    }
}