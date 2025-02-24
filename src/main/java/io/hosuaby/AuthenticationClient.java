package io.hosuaby;

public class AuthenticationClient {
    public static void main(String[] args) {
        var authentication = Authentication.cognito();
        var token = authentication.getToken();
        System.out.println("Token: " + token);
    }
}
