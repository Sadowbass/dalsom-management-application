package com.dalsom.management.admin.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AdminForm {

    @NotEmpty(message = "아이디는 필수입니다")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수입니다")
    private String password;

    @NotEmpty(message = "이름은 필수입니다")
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
