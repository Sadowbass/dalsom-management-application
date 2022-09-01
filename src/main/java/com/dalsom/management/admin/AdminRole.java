package com.dalsom.management.admin;

public enum AdminRole {
    MASTER("길드마스터"), ADMIN("운영진"), DEVELOPER("개발자");

    private String koreanName;

    AdminRole(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
