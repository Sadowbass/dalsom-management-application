package com.dalsom.management.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AdminForm {

    @NotEmpty(message = "required : loginId")
    private String loginId;

    @NotEmpty(message = "required : password")
    private String password;

    @NotEmpty(message = "required : name")
    private String name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdminForm{");
        sb.append("loginId='").append(loginId).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
