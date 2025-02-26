package io.hosuaby;

import java.util.Random;

public sealed interface Authentication permits Authentication.AbstractAuthentication {
    static Authentication cognito() {
        return new CognitoAuthentication(baseUrl() + "/cognito");
    }

    static Authentication auth0() {
        return new Auth0Authentication(baseUrl() + "/auth0");
    }

    private static String baseUrl() {
        return "https://site.com";
    }

    default String getToken() {
        return "<token_%d>".formatted(salt());
    }

    private int salt() {
        return new Random().nextInt(600_000);
    }

    abstract sealed class AbstractAuthentication
            implements Authentication
            permits Authentication.CognitoAuthentication, Authentication.Auth0Authentication {
        private final String url;

        public AbstractAuthentication(final String url) {
            this.url = url;
        }
    }

    final class CognitoAuthentication extends AbstractAuthentication {
        public CognitoAuthentication(final String url) {
            super(url);
        }

        @Override
        public String getToken() {
            return "cognito" + super.getToken();
        }
    }

    final class Auth0Authentication extends AbstractAuthentication {
        public Auth0Authentication(final String url) {
            super(url);
        }

        @Override
        public String getToken() {
            return "auth0" + super.getToken();
        }
    }
}
