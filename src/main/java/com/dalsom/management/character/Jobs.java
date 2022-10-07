package com.dalsom.management.character;

import org.springframework.util.ObjectUtils;

public enum Jobs {
    디스트로이어, 워로드, 버서커, 홀리나이트,
    배틀마스터, 인파이터, 기공사, 창술사, 스트라이커,
    데빌헌터, 블래스터, 호크아이, 스카우터, 건슬링어,
    바드, 서머너, 아르카나, 소서리스,
    블레이드, 데모닉, 리퍼,
    도화가, 기상술사,
    확인불가;

    Jobs() {
    }

    public static Jobs findJob(String jobName) {
        if (ObjectUtils.isEmpty(jobName)) {
            return Jobs.확인불가;
        }
        
        try {
            return Jobs.valueOf(jobName);
        } catch (IllegalArgumentException e) {
            return Jobs.확인불가;
        }
    }
}
