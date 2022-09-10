package com.dalsom.management.admin;

public enum AdminRole {
    DEVELOPER("개발자", 0),
    MASTER("길드마스터", 1),
    ADMIN("운영진", 2),
    UNKNOWN("미확인", 99);

    private String koreanName;
    private int privilege;

    AdminRole(String koreanName, int privilege) {
        this.koreanName = koreanName;
        this.privilege = privilege;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public int getPrivilege() {
        return privilege;
    }

    public boolean canChangeTarget(String admin, String target) {
        AdminRole adminRole = AdminRole.valueOf(admin);
        AdminRole targetRole = AdminRole.valueOf(target);

        return adminRole.canChangeTarget(targetRole);
    }

    public boolean canChangeTarget(AdminRole target) {
        return this.getPrivilege() <= target.getPrivilege();
    }
}
