package com.vacinasja.dto.login;

public class LoginDto {

    private String login;
    private String password;

    public LoginDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginDto() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
