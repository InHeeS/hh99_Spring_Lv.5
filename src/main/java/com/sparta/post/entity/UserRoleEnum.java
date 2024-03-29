package com.sparta.post.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserRoleEnum {

    USER(Authority.USER),  // 사용자 권한, 생성자의 값을 넣어준다.
    ADMIN(Authority.ADMIN);  // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
