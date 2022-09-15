package com.dalsom.management.guild;

import lombok.Getter;

@Getter
public enum GuildRole {
    MASTER("길드마스터"), ADMIN("운영진"), MEMBER("길드원");

    private final String koreanName;

    GuildRole(String koreanName) {
        this.koreanName = koreanName;
    }
}
